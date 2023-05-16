package com.turkcell.filterservice.repository;

import com.turkcell.filterservice.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FilterRepository extends MongoRepository<Filter, UUID> {
    boolean existsByBrandId(UUID brandId);
    boolean existsByCarId(UUID carId);
    void deleteByCarId(UUID id);
    void deleteAllByBrandId(UUID brandId);
    Filter findByCarId(UUID carId);
}
