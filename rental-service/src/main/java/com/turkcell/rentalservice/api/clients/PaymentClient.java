package com.turkcell.rentalservice.api.clients;


import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.commonpackage.utils.dto.CreateRentalPaymentRequest;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "payment-service", fallback = PaymentClientFallback.class)
public interface PaymentClient {

    @Retry(name = "retry-payment-client")
    @PutMapping("/api/payments/process-rental-payment")
    ClientResponse processPayment(CreateRentalPaymentRequest request);
}
