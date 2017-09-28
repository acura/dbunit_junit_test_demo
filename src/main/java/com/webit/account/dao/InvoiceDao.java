package com.webit.account.dao; 

import java.util.List;

import com.webit.account.dto.Invoice;

/**
 * Repository for entity Invoice
 */
public interface InvoiceDao
{
    /**
     * retrieve all Invoices
     */
    List<Invoice> retrieveInvoices();
    
   /**
   * retrieve Invoice by given id
   */
   Invoice retrieveInvoice(Long id);

   /**
    * create Invoice and return the id
    */
   Long createInvoice(Invoice invoice);

   /**
   * update the given Invoice
   */
   boolean updateInvoice(Invoice invoice);

   /**
    * delete Invoice by given id
 * @return 
    */
   void deleteInvoice(Long id);

}