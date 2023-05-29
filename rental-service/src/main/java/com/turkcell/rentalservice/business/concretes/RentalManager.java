package com.turkcell.rentalservice.business.concretes;

import com.turkcell.commonpackage.event.rental.RentalCreatedEvent;
import com.turkcell.commonpackage.event.rental.RentalDeletedEvent;
import com.turkcell.commonpackage.invoice.InvoiceCreatedEvent;
import com.turkcell.commonpackage.utils.dto.GetCarResponse;
import com.turkcell.commonpackage.utils.kafka.producer.KafkaProducer;
import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import com.turkcell.rentalservice.api.clients.CarClient;
import com.turkcell.rentalservice.business.abstracts.RentalService;
import com.turkcell.rentalservice.business.dto.requests.CreateRentalRequest;
import com.turkcell.rentalservice.business.dto.requests.UpdateRentalRequest;
import com.turkcell.rentalservice.business.dto.responses.CreateRentalResponse;
import com.turkcell.rentalservice.business.dto.responses.GetAllRentalsResponse;
import com.turkcell.rentalservice.business.dto.responses.GetRentalResponse;
import com.turkcell.rentalservice.business.dto.responses.UpdateRentalResponse;
import com.turkcell.rentalservice.business.rules.RentalBusinessRules;
import com.turkcell.rentalservice.entities.Rental;
import com.turkcell.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessRules rules;
    private final KafkaProducer producer;
    private final CarClient carClient;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        var rentals = repository.findAll();
        var response = rentals
                .stream()
                .map(rental -> mapper.forResponse().map(rental, GetAllRentalsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        rules.checkIfRentalExists(id);
        var rental = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rules.ensureCarIsAvailable(request.getCarId());

        var rental = mapper.forRequest().map(request, Rental.class);
        var car = carClient.getById(request.getCarId());
        rental.setId(null);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setRentedAt(LocalDate.now());
        request.getPaymentRequest().setPrice(rental.getTotalPrice());

        rules.processPayment(request.getPaymentRequest());
        repository.save(rental);

        sendKafkaRentalCreatedEvent(request.getCarId());
        sendKafkaInvoiceCreatedEvent(rental,car,request.getPaymentRequest().getCardHolder());

        var response = mapper.forResponse().map(rental, CreateRentalResponse.class);

        return response;
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);

        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        repository.save(rental);

        var response = mapper.forResponse().map(rental, UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfRentalExists(id);
        sendKafkaRentalDeletedEvent(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

    private void sendKafkaRentalCreatedEvent(UUID carId) {
        producer.sendMessage("rental-created",new RentalCreatedEvent(carId));
    }

    private void sendKafkaRentalDeletedEvent(UUID id) {
        var carId = repository.findById(id).orElseThrow().getCarId();
        producer.sendMessage("rental-deleted",new RentalDeletedEvent(carId));
    }

    private void sendKafkaInvoiceCreatedEvent(Rental rental, GetCarResponse carResponse, String cardHolder){
        InvoiceCreatedEvent invoiceCreatedEvent = new InvoiceCreatedEvent();
        invoiceCreatedEvent.setCardHolder(cardHolder);
        invoiceCreatedEvent.setModelName(carResponse.getModelName());
        invoiceCreatedEvent.setBrandName(carResponse.getBrandName());
        invoiceCreatedEvent.setPlate(carResponse.getPlate());
        invoiceCreatedEvent.setModelYear(carResponse.getModelYear());
        invoiceCreatedEvent.setDailyPrice(rental.getDailyPrice());
        invoiceCreatedEvent.setTotalPrice(rental.getTotalPrice());
        invoiceCreatedEvent.setRentedForDays(rental.getRentedForDays());
        invoiceCreatedEvent.setRentedAt(LocalDateTime.now());

        producer.sendMessage("invoice-created",invoiceCreatedEvent);
    }
}