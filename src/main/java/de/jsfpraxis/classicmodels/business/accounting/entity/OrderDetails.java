package de.jsfpraxis.classicmodels.business.accounting.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.jsfpraxis.classicmodels.business.products.entity.Product;

/**
 *  Innerhalb einer Bestellung definiert die Bestellposition die Reihenfolge der Positionen
 *  im Property 'position' beginnend ab 1.
 *  
 *  @author bernd
 *
 */
@Entity
@IdClass(OrderDetailsId.class)
@Table(name = "OrderDetails")
public class OrderDetails {

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "OrderNumber")
	private Order order;
	
	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "ProductCode")
	private Product product;

	@Column(name = "OrderLineNumber", nullable = false)
	private Integer position;
	
	@Column(nullable = false)
	private Integer quantityOrdered;

	@Column(nullable = false)
	private BigDecimal priceEach;
	  
	
	public OrderDetails() {
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Integer getPosition() {
		return position;
	}


	public void setPosition(Integer position) {
		this.position = position;
	}


	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}


	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}


	public BigDecimal getPriceEach() {
		return priceEach;
	}


	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}





}
