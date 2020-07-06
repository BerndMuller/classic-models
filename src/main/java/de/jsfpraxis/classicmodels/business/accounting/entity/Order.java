package de.jsfpraxis.classicmodels.business.accounting.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Orders")
@NamedQuery(name = "Order.findAll", query = "SELECT o from Order o LEFT JOIN FETCH o.customer")
@NamedQuery(name = "Order.findOrdersForCustomer", query = "SELECT o from Order o LEFT JOIN FETCH o.customer where o.customer.id = :customerId")
@NamedQuery(name = "Order.count", query = "SELECT count(o) FROM Order o")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderNumber")
	private Integer id;

	@NotNull
	private LocalDate orderDate;

	@NotNull
	private LocalDate requiredDate;
	
	private LocalDate shippedDate;

	@NotNull
	private OrderStatus status;

	@Column(length = 1000)
	private String comments;

	@ManyToOne
	@JoinColumn(name = "CustomerNumber", nullable = false)
	private Customer customer; 

	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	@OrderBy("position")
	private List<OrderDetails> orderDetails;
	


	public Order() {
	}


	// Getter und Setter
	public Integer getId() {
		return id;
	}
//	public void setId(Integer id) {
//		this.id = id;
//	}

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

	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
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

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
