package com.turkcell.paymentservice.business.concretes;


import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcell.commonpackage.utils.exceptions.BusinessException;
import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import com.turkcell.paymentservice.business.abstracts.PaymentService;
import com.turkcell.paymentservice.business.abstracts.PosService;
import com.turkcell.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.turkcell.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.turkcell.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.turkcell.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.turkcell.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.turkcell.paymentservice.business.dto.responses.update.UpdatePaymentResponse;
import com.turkcell.paymentservice.business.rules.PaymentBusinessRules;
import com.turkcell.paymentservice.entities.Payment;
import com.turkcell.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final ModelMapperService mapper;
    private final PaymentRepository repository;
    private final PaymentBusinessRules rules;
    private final PosService posService;


    @Override
    public List<GetAllPaymentsResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<GetAllPaymentsResponse> response = payments
                .stream()
                .map(payment -> mapper.forRequest().map(payment, GetAllPaymentsResponse.class)).toList();

        return response;
    }

    @Override
    public GetPaymentResponse getById(UUID id) {
        rules.checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.forRequest().map(payment, GetPaymentResponse.class);

        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest request) {
        rules.checkIfCardExists(request);

        Payment payment = mapper.forResponse().map(request, Payment.class);
        payment.setId(null);
        repository.save(payment);
        CreatePaymentResponse response = mapper.forRequest().map(payment, CreatePaymentResponse.class);

        return response;
    }


    @Override
    public UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request) {
        rules.checkIfPaymentExists(id);
        Payment payment = mapper.forResponse().map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        UpdatePaymentResponse response = mapper.forRequest().map(payment, UpdatePaymentResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public ClientResponse processRentalPayment(CreateRentalPaymentRequest request) {
        ClientResponse response = new ClientResponse();
        try{
            rules.checkIfPaymentIsValid(request);
            Payment payment = repository.findByCardNumber(request.getCardNumber());
            rules.checkIfBalanceIsEnough(request.getPrice(), payment.getBalance());
            posService.pay();

            payment.setBalance(payment.getBalance() - request.getPrice());
            repository.save(payment);
            response.setSuccess(true);
        }
        catch (BusinessException exception){
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }

        return response;
    }
}