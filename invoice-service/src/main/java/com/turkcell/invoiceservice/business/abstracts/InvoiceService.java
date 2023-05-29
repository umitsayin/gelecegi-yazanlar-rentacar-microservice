package com.turkcell.invoiceservice.business.abstracts;



import com.turkcell.invoiceservice.business.dto.requests.create.CreateInvoiceRequest;
import com.turkcell.invoiceservice.business.dto.requests.update.UpdateInvoiceRequest;
import com.turkcell.invoiceservice.business.dto.responses.create.CreateInvoiceResponse;
import com.turkcell.invoiceservice.business.dto.responses.get.GetAllInvoicesResponse;
import com.turkcell.invoiceservice.business.dto.responses.get.GetInvoiceResponse;
import com.turkcell.invoiceservice.business.dto.responses.update.UpdateInvoiceResponse;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(UUID id);
    CreateInvoiceResponse add(CreateInvoiceRequest request);
    UpdateInvoiceResponse update(UUID id, UpdateInvoiceRequest request);
    void delete(UUID id);
}
