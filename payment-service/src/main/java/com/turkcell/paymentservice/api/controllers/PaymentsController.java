package com.turkcell.paymentservice.api.controllers;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcell.paymentservice.business.abstracts.PaymentService;
import com.turkcell.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.turkcell.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.turkcell.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.turkcell.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.turkcell.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.turkcell.paymentservice.business.dto.responses.update.UpdatePaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request){
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable UUID id, @Valid @RequestBody UpdatePaymentRequest request){
        return service.update(id,request);
    }

    @PutMapping("/process-rental-payment")
    public ClientResponse processRentalPayment(@RequestBody CreateRentalPaymentRequest request){
        return service.processRentalPayment(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
}