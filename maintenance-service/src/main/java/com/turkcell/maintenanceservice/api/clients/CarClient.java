package com.turkcell.maintenanceservice.api.clients;

import com.turkcell.commonpackage.utils.dto.ClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;


@FeignClient(name = "INVENTORY-SERVICE",fallback = CarClientFallback.class)
public interface CarClient {

    @Retry(name="retry-maintenance")
    @GetMapping(value = "/api/cars/check-car-available/{carId}")
    ClientResponse checkIfCarAvailable(@PathVariable UUID carId);
}