package de.jsfpraxis.classicmodels.business.accounting.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(PaymentId.class)
@Table(name = "Payments")
@NamedQueries(
	@NamedQuery(name = "Payment.getAll", query = "SELECT p from Payment p")
)
public class Payment {

	@Id
	private Integer customerNumber;
	
	@Id
	@Column(length = 50)
	private String checkNumber;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date paymentDate;

	@Column(nullable = false)
	private BigDecimal amount;
	  
	public Payment() {
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
}
