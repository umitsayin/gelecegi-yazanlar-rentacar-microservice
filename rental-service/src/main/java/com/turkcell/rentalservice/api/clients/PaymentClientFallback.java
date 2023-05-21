package com.turkcell.rentalservice.api.clients;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcell.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class PaymentClientFallback implements PaymentClient{
    @Override
    public ClientResponse processPayment(CreateRentalPaymentRequest paymentRequest) {
         throw new BusinessException("PAYMENTSERVICE_IS_DOWN");
    }
}
