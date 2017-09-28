package com.webit.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webit.account.dto.Invoice;
import com.webit.account.service.InvoiceService;

/**
 * Resource Implementation of User Invoices.
 */
@RestController
public class InvoiceController
{

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/users/accounts/invoices", method = RequestMethod.GET)
    public ResponseEntity<List<Invoice>> retrieveInvoices()
    {
        List<Invoice> result = invoiceService.retrieveAllInvoice();
        if (result == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/accounts/invoices/{invoiceNo}", method = RequestMethod.GET)
    public ResponseEntity<Invoice> retrieveInvoice(@PathVariable("invoiceNo") String invoiceNo)
    {
        Invoice result = invoiceService.retrieveInvoice(invoiceNo);
        if (result == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
