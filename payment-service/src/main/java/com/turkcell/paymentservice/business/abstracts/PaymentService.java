package com.turkcell.paymentservice.business.abstracts;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcell.paymentservice.business.dto.requests.create.CreatePaymentRequest;
import com.turkcell.paymentservice.business.dto.requests.update.UpdatePaymentRequest;
import com.turkcell.paymentservice.business.dto.responses.create.CreatePaymentResponse;
import com.turkcell.paymentservice.business.dto.responses.get.GetAllPaymentsResponse;
import com.turkcell.paymentservice.business.dto.responses.get.GetPaymentResponse;
import com.turkcell.paymentservice.business.dto.responses.update.UpdatePaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(UUID id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request);
    void delete(UUID id);
    ClientResponse processRentalPayment(CreateRentalPaymentRequest request);
}