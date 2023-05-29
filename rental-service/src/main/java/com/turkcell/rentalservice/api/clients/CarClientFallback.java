package com.turkcell.rentalservice.api.clients;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import com.turkcell.commonpackage.utils.dto.GetCarResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CarClientFallback implements CarClient{

    @Override
    public ClientResponse checkIfCarAvailable(UUID carId) {
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }

    @Override
    public GetCarResponse getById(UUID carId) {
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }
}
