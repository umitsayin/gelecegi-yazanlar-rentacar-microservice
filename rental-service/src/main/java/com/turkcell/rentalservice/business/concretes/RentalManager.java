package com.turkcell.rentalservice.business.concretes;

import com.turkcell.commonpackage.event.rental.RentalCreatedEvent;
import com.turkcell.commonpackage.event.rental.RentalDeletedEvent;
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
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessRules rules;
    private final CarClient carClient;
    private final KafkaProducer producer;

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
        carClient.checkIfCarAvailable(request.getCarId());
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(null);
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setRentedAt(LocalDate.now());
        repository.save(rental);
        sendKafkaRentalCreatedEvent(request.getCarId());
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
        var carId = repository.findById(id).orElseThrow().getCarId();
        producer.sendMessage("rental-deleted",new RentalDeletedEvent(carId));
        repository.deleteById(id);
    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

    private void sendKafkaRentalCreatedEvent(UUID carId) {
        producer.sendMessage("rental-created",new RentalCreatedEvent(carId));
    }
}