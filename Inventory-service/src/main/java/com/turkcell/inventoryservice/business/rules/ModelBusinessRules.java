package com.turkcell.inventoryservice.business.rules;

import com.turkcell.inventoryservice.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository repository;

    public void checkIfModelExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("MODEL_NOT_EXISTS");
        }
    }
}
