package com.turkcell.invoiceservice.business.concretes;


import com.turkcell.commonpackage.utils.mappers.ModelMapperService;
import com.turkcell.invoiceservice.business.abstracts.InvoiceService;
import com.turkcell.invoiceservice.business.dto.requests.create.CreateInvoiceRequest;
import com.turkcell.invoiceservice.business.dto.requests.update.UpdateInvoiceRequest;
import com.turkcell.invoiceservice.business.dto.responses.create.CreateInvoiceResponse;
import com.turkcell.invoiceservice.business.dto.responses.get.GetAllInvoicesResponse;
import com.turkcell.invoiceservice.business.dto.responses.get.GetInvoiceResponse;
import com.turkcell.invoiceservice.business.dto.responses.update.UpdateInvoiceResponse;
import com.turkcell.invoiceservice.business.rules.InvoiceBusinessRules;
import com.turkcell.invoiceservice.entities.Invoice;
import com.turkcell.invoiceservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapperService mapper;
    private final InvoiceBusinessRules rules;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> response = invoices
                .stream()
                .map(invoice -> mapper.forResponse().map(invoice, GetAllInvoicesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetInvoiceResponse getById(UUID id) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.forResponse().map(invoice, GetInvoiceResponse.class);

        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);
        invoice.setId(null);
        invoice.setTotalPrice(getTotalPrice(invoice));
        CreateInvoiceResponse response = mapper.forResponse().map(invoice, CreateInvoiceResponse.class);

        return response;
    }

    @Override
    public UpdateInvoiceResponse update(UUID id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);
        invoice.setId(id);
        repository.save(invoice);
        UpdateInvoiceResponse response = mapper.forResponse().map(invoice, UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfInvoiceExists(id);
        repository.deleteById(id);
    }


    private double getTotalPrice(Invoice invoice) {
        return invoice.getDailyPrice() * invoice.getRentedForDays();
    }
}
