package com.turkcell.inventoryservice.business.rules;

import com.turkcell.inventoryservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;

    public void checkIfBrandExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("BRAND_NOT_EXISTS");
        }
    }
}
