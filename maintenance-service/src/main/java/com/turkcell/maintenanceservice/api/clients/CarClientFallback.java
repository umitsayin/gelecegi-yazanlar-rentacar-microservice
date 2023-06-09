package com.turkcell.maintenanceservice.api.clients;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class CarClientFallback implements CarClient {

    @Override
    public ClientResponse checkIfCarAvailable(UUID carId) {
        throw new RuntimeException("SERVER IS DOWN");
    }
}
