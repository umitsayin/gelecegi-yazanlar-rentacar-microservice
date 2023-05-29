package com.turkcell.invoiceservice.business.kafka.consumer;

import com.turkcell.commonpackage.invoice.InvoiceCreatedEvent;
import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import com.turkcell.invoiceservice.business.abstracts.InvoiceService;
import com.turkcell.invoiceservice.business.dto.requests.create.CreateInvoiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final InvoiceService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "invoice-created",
            groupId = "invoice-rental-create"
    )
    public void consume(InvoiceCreatedEvent event) {
       var request = mapper.forRequest().map(event, CreateInvoiceRequest.class);
       service.add(request);
    }
}
