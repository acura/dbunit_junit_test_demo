package com.webit.account.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Data transfer object for Invoice.
 */

public class Invoice implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date invoiceDate;
	private String invoiceNo;
	private String currency;
	private Double vat;
	private Double vatAmount;
	private Double netAmount;
	private Double totalAmount;
	private Double discount;
	private Double discountAmount;

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoice(Long id, Date invoiceDate, String invoiceNo, String currency, Double vat, Double vatAmount,
			Double netAmount, Double totalAmount, Double discount, Double discountAmount) {
		super();
		this.id = id;
		this.invoiceDate = invoiceDate;
		this.invoiceNo = invoiceNo;
		this.currency = currency;
		this.vat = vat;
		this.vatAmount = vatAmount;
		this.netAmount = netAmount;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.discountAmount = discountAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public Double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoiceDate=" + invoiceDate + ", invoiceNo=" + invoiceNo + ", currency="
				+ currency + ", vat=" + vat + ", vatAmount=" + vatAmount + ", netAmount=" + netAmount + ", totalAmount="
				+ totalAmount + ", discount=" + discount + ", discountAmount=" + discountAmount + "]";
	}

}
