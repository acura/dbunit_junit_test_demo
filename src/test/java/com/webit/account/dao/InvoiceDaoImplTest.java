package com.webit.account.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.SimpleDateFormat;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.webit.account.dto.Invoice;

// FIXME enable the test
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(locations = "classpath:/spring/spring-test-config.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InvoiceDaoImplTest {

	@Autowired
	private InvoiceDao dao;
	Date date;

	private Invoice invoice;

	public InvoiceDaoImplTest() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateStr = formatter.parse("2017-09-24");
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			invoice = new Invoice(3L, dateDB, "INV000001", "USD", 10D, 200d, 400d, 500d, 1d, 10d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = "/data/createInvoiceData.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, table = "T_INVOICE", value = "/data/createInvoiceExpectedData.xml")
	public void testAshouldCreateInvoice() {
		dao.createInvoice(invoice);
		Invoice invoice1 = dao.retrieveInvoice(3L);

		assertEquals("test for date is correct:", invoice1.getInvoiceDate(), invoice.getInvoiceDate());
		assertEquals("test for invoice number is correct:", invoice1.getInvoiceNo(), invoice.getInvoiceNo());
		assertEquals("test for currency is correct:", invoice1.getInvoiceNo(), invoice.getInvoiceNo());
		assertEquals("test for vat is correct:", invoice1.getVat(), invoice.getVat());
		assertEquals("test for vat ammount is correct:", invoice1.getVatAmount(), invoice.getVatAmount());
		assertEquals("test for net amount is correct:", invoice1.getNetAmount(), invoice.getNetAmount());
		assertEquals("test for total amount is correct:", invoice1.getTotalAmount(), invoice.getTotalAmount());
		assertEquals("test for discount is correct:", invoice1.getDiscount(), invoice.getDiscount());
		assertEquals("test for discount amount is correct:", invoice1.getDiscountAmount(), invoice.getDiscountAmount());

	}

	@Test
	@DatabaseSetup("/data/retrieveInvoice.xml")
	public void testBshouldRetrieveInvoice() {
		Invoice invoice1 = dao.retrieveInvoice(3L);

		assertEquals("test for date is correct:", invoice1.getInvoiceDate(), invoice.getInvoiceDate());
		assertEquals("test for invoice number is correct:", invoice1.getInvoiceNo(), invoice.getInvoiceNo());
		assertEquals("test for currency is correct:", invoice1.getInvoiceNo(), invoice.getInvoiceNo());
		assertEquals("test for vat is correct:", invoice1.getVat(), invoice.getVat());
		assertEquals("test for vat ammount is correct:", invoice1.getVatAmount(), invoice.getVatAmount());
		assertEquals("test for net amount is correct:", invoice1.getNetAmount(), invoice.getNetAmount());
		assertEquals("test for total amount is correct:", invoice1.getTotalAmount(), invoice.getTotalAmount());
		assertEquals("test for discount is correct:", invoice1.getDiscount(), invoice.getDiscount());
		assertEquals("test for discount amount is correct:", invoice1.getDiscountAmount(), invoice.getDiscountAmount());
	}

	@Test
	@DatabaseSetup(value = "/data/updateInvoiceData.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/data/updateInvoiceExpectedData.xml")
	public void testCshouldUpdateExistingInvoice() {
		Invoice invoice1 = dao.retrieveInvoice(3L);
		invoice1.setCurrency("UK");
		invoice1.setDiscountAmount(22.00);
		dao.updateInvoice(invoice1);

		Invoice invoice2 = dao.retrieveInvoice(3L);
		System.out.println(invoice2.getCurrency());
		assertEquals("test for currency is correct:", invoice2.getCurrency(), invoice1.getCurrency());
		assertEquals("test for discount amount is correct:", invoice2.getDiscountAmount(),
				invoice1.getDiscountAmount());

	}

	@Test
	@DatabaseTearDown(type = DatabaseOperation.DELETE, value = "/data/deleteInvoiceData.xml")
	@ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "/data/deleteInvoiceExpectedData.xml")
	public void testDshouldDeleteExistingInvoice() {
		dao.deleteInvoice(3L);
		assertEquals("test for invoice with id 3L is deleted ", null, dao.retrieveInvoice(3L));
	}
}