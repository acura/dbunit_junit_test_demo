package com.webit.account.service;

import java.util.List;

import com.webit.account.dto.Invoice;

/**
 * Generated interface for the Service InvoiceService.
 */
public interface InvoiceService
{
    
    public List<Invoice> retrieveAllInvoice();
    
    public Invoice retrieveInvoice(String invoiceNo);

    public Long createInvoice(Invoice invoice);

    public boolean updateInvoice(Invoice invoice);
    
    public void deleteInvoice(Long id);

}
