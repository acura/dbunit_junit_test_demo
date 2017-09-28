package com.webit.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webit.account.dao.InvoiceDao;
import com.webit.account.dto.Invoice;

/**
 * Implementation of InvoiceService.
 */
@Service
public class InvoiceServiceImpl implements InvoiceService
{
    @Autowired
    private InvoiceDao invoiceDao;

    @Override
    public List<Invoice> retrieveAllInvoice()
    {
        return invoiceDao.retrieveInvoices();
    }

    @Override
    public Invoice retrieveInvoice(String invoiceNo)
    {
        Invoice invoice = invoiceDao.retrieveInvoice(1L);

        return invoice;
    }

    @Override
    public Long createInvoice(Invoice invoice)
    {
        return invoiceDao.createInvoice(invoice);
    }

    @Override
    public boolean updateInvoice(Invoice invoice)
    {
        return invoiceDao.updateInvoice(invoice);
    }
    
    @Override
    public void deleteInvoice(Long id)
    {
       invoiceDao.deleteInvoice(id);
    }

   
}
