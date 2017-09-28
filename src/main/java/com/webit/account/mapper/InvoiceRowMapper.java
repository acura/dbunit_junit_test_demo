package com.webit.account.mapper;  

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.webit.account.dto.Invoice;

/**
 * Row mapper for entity Invoice
 */
public class InvoiceRowMapper implements RowMapper<Invoice>
{

    @Override
    public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getLong("ID"));
        invoice.setInvoiceDate(rs.getDate("INVOICE_DATE"));
        invoice.setInvoiceNo(rs.getString("INVOICE_NO"));
        invoice.setCurrency(rs.getString("CURRENCY"));
        invoice.setVat(rs.getDouble("VAT"));
        invoice.setVatAmount(rs.getDouble("VAT_AMOUNT"));
        invoice.setNetAmount(rs.getDouble("NET_AMOUNT"));
        invoice.setTotalAmount(rs.getDouble("TOTAL_AMOUNT"));
        invoice.setDiscount(rs.getDouble("DISCOUNT"));
        invoice.setDiscountAmount(rs.getDouble("DISCOUNT_AMOUNT"));

        return invoice;
    }

}