package com.turkcell.inventoryservice.business.rules;

import com.turkcell.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;

    public void checkIfCarExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("CAR_NOT_EXISTS");
        }
    }
}
