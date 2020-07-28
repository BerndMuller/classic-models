package de.jsfpraxis.classicmodels.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.accounting.boundary.OrderService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Order;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderDetails;

@Named
@RequestScoped
public class OrderDetailsController {

	private Integer orderId; // view param
	
	private Order order;

	@Inject
	OrderService orderService;
	
	public OrderDetailsController() {
	}

	public void viewAction() {
		order = orderService.find(orderId);
	}
	
	public String viewAction2() {
		if (orderId == null) {
			return "orders.xhtml?page=1&faces-redirect=true";	
		} else {
			order = orderService.find(orderId);
			return null;
		}
	}

	public List<OrderDetails> getOrderDetails() {
		if (orderId == null) {
			return List.of();
		} else {
			return order.getOrderDetails();	
		}
	}
	
	// Getter und Setter
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}
