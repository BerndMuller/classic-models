package de.jsfpraxis.classicmodels.business.accounting.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
@NamedQuery(name = "Order.findAll", query = "SELECT o from Order o LEFT JOIN FETCH o.customer")
@NamedQuery(name = "Order.findOrdersForCustomer", query = "SELECT o from Order o LEFT JOIN FETCH o.customer where o.customer.id = :customerId")
@NamedQuery(name = "Order.count", query = "SELECT count(o) FROM Order o")
public class Order {

	@Id
	@Column(name = "OrderNumber")
	private Integer id;

	@Column(nullable = false)
	private LocalDate orderDate;

	@Column(nullable = false)
	private LocalDate requiredDate;
	
	private LocalDate shippedDate;

	@Column(length = 15, nullable = false)
	private String status;

	@Column(length = 1000)
	private String comments;

	@ManyToOne
	@JoinColumn(name = "CustomerNumber", nullable = false)
	private Customer customer; 
	  
	
	public Order() {
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}


	public LocalDate getRequiredDate() {
		return requiredDate;
	}


	public void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}


	public LocalDate getShippedDate() {
		return shippedDate;
	}


	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}




}
