package com.webit.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.webit.account.dto.Invoice;
import com.webit.account.mapper.InvoiceRowMapper;

/**
 * Repository for entity Invoice
 */
@Repository
@Transactional
public class InvoiceDaoImpl implements InvoiceDao {
	private Logger logger = LoggerFactory.getLogger(InvoiceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Invoice> retrieveInvoices() {
		try {
			List<Invoice> invoices = jdbcTemplate.query("SELECT * FROM T_INVOICE", new InvoiceRowMapper());
			logger.debug("Retrieve Invoice Id = {}");
			return invoices;
		} catch (Exception e) {
			logger.debug("Invoice not found by id {}");
			logger.trace("Invoice not found by id {}", e);
		}
		return null;
	}

	/**
	 * retrieve Invoice by given id
	 */
	@Override
	public Invoice retrieveInvoice(Long id) {
		try {
			Invoice invoice = jdbcTemplate.queryForObject("SELECT * FROM T_INVOICE WHERE id = ?", new Object[] { id },
					new InvoiceRowMapper());
			logger.debug("Retrieve Invoice Id = {} ", id);
			return invoice;
		} catch (Exception e) {
			logger.debug("Invoice not found by id {}", id);
			logger.trace("Invoice not found by id {}", id, e);
		}
		return null;
	}

	/**
	 * create Invoice and return the id
	 */
	// TODO check the method for bugs and fix it some
	@Override
	public Long createInvoice(final Invoice invoice) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int created = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO T_INVOICE (INVOICE_DATE,INVOICE_NO,CURRENCY,VAT, VAT_AMOUNT, NET_AMOUNT,TOTAL_AMOUNT,DISCOUNT, DISCOUNT_AMOUNT)"
								+ " VALUES (?,?,?,?,?,?,?,?,?)",
						new String[] { "id" });

				if (invoice.getInvoiceDate() == null) {
					ps.setTimestamp(1, null);
				} else {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					ps.setString(1, dateFormat.format(invoice.getInvoiceDate()));
				}
				ps.setString(2, invoice.getInvoiceNo());
				ps.setString(3, invoice.getCurrency());
				ps.setString(4, invoice.getVat() + "");
				ps.setString(5, invoice.getVatAmount() + "");
				ps.setString(6, invoice.getNetAmount() + "");
				ps.setString(7, invoice.getTotalAmount() + "");
				ps.setString(8, invoice.getDiscount() + "");
				ps.setString(9, invoice.getDiscountAmount() + "");

				return ps;
			}
		}, keyHolder);

		if (created <= 0) {
			System.out.println("Not created");
			return null;
		}

		long id = keyHolder.getKey().longValue();
		System.out.println("In create invoice accountid" + id);
		logger.debug("Added Invoice {} successfully", id);
		return id;
	}

	/**
	 * update the given Invoice
	 */
	// TODO check the method for bugs and fix it some
	@Override
	public boolean updateInvoice(Invoice invoice) {
		String updateQuery = "UPDATE T_INVOICE SET INVOICE_DATE=?, INVOICE_NO=?, CURRENCY=?, VAT=?, VAT_AMOUNT=?,NET_AMOUNT=?, TOTAL_AMOUNT=?,DISCOUNT=?, DISCOUNT_AMOUNT=? WHERE ID=?";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Object[] params = new Object[] { dateFormat.format(invoice.getInvoiceDate()), invoice.getInvoiceNo(),
				invoice.getCurrency(), invoice.getVat(), invoice.getVatAmount(), invoice.getNetAmount(),
				invoice.getTotalAmount(), invoice.getDiscount(), invoice.getDiscountAmount(), invoice.getId() };

		int[] types = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT };

		int updated = jdbcTemplate.update(updateQuery, params, types);
		if (updated > 0) {
			System.out.println("data updated susccessfully");
			logger.debug("Updated Invoice {} successfully", invoice.getId());
		}
		return updated > 0;
	}

	/**
	 * delete Invoice by given id
	 */
	public void deleteInvoice(Long id) {

		int deleted = jdbcTemplate.update("DELETE FROM T_INVOICE WHERE ID=?", id);
		if (deleted > 0) {
			logger.debug("Delete Account with ID = {}", id);
		}
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}