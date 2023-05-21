package com.turkcell.rentalservice.business.rules;

import com.turkcell.commonpackage.utils.constants.Messages;
import com.turkcell.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.turkcell.commonpackage.utils.exceptions.BusinessException;
import com.turkcell.rentalservice.api.clients.CarClient;
import com.turkcell.rentalservice.api.clients.PaymentClient;
import com.turkcell.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;
    private final CarClient carClient;
    private final PaymentClient paymentClient;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("MODEL_NOT_EXISTS");
        }
    }

    public void ensureCarIsAvailable(UUID carId) {
        var response = carClient.checkIfCarAvailable(carId);
        if (!response.isSuccess()) {
            throw new BusinessException(response.getMessage());
        }
    }

    public void processPayment(CreateRentalPaymentRequest request){
        var response = paymentClient.processPayment(request);

        if(!response.isSuccess()){
            throw new BusinessException(Messages.Payment.Failed);
        }
    }
}
