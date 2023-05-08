package com.turkcell.filterservice.kafka.consumer;

import com.turkcell.commonpackage.event.inventory.BrandDeletedEvent;
import com.turkcell.commonpackage.event.inventory.CarCreatedEvent;
import com.turkcell.commonpackage.event.inventory.CarDeletedEvent;
import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import com.turkcell.filterservice.business.abstracts.FilterService;
import com.turkcell.filterservice.entities.Filter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private final FilterService service;
    private final ModelMapperService modelMapper;

    @KafkaListener(topics = "car-created", groupId = "car-create")
    public void consume(CarCreatedEvent event){
        var filter = modelMapper.forRequest().map(event, Filter.class);
        service.add(filter);
        log.info("car-created consume");
    }

    @KafkaListener(topics = "car-deleted", groupId = "car-delete")
    public void consume(CarDeletedEvent event){
        service.delete(event.getCarId());
        log.info("car-delete consume");
    }

    @KafkaListener(topics = "brand-deleted", groupId = "brand-delete")
    public void consume(BrandDeletedEvent event){
        service.deleteAllByBrandId(event.getBrandId());
        log.info("car-delete consume");
    }
}
