package com.turkcell.invoiceservice.business.rules;

import com.turkcell.commonpackage.utils.constants.Messages;
import com.turkcell.commonpackage.utils.exceptions.BusinessException;
import com.turkcell.invoiceservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class InvoiceBusinessRules {
    private final InvoiceRepository repository;

    public void checkIfInvoiceExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Invoice.INVOICE_NOT_FOUND);
        }
    }
}
