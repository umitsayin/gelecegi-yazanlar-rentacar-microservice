package com.turkcell.inventoryservice.kafka.consumer;

import com.turkcell.commonpackage.event.rental.RentalCreatedEvent;
import com.turkcell.commonpackage.event.rental.RentalDeletedEvent;
import com.turkcell.inventoryservice.business.abstracts.CarService;
import com.turkcell.inventoryservice.entities.enums.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final CarService service;

    @KafkaListener(topics = "rental-created", groupId = "inventory-rental-create")
    public void consume(RentalCreatedEvent event) {
        service.changeStateByCarId(State.Rented, event.getCarId());
        log.info("Rental created event consumed {}", event);
    }

    @KafkaListener(topics = "rental-deleted", groupId = "inventory-rental-delete")
    public void consume(RentalDeletedEvent event) {
        service.changeStateByCarId(State.Available, event.getCarId());
    }
}
