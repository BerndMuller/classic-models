package de.jsfpraxis.classicmodels.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.accounting.boundary.OrderService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Order;

@Named
@RequestScoped
public class OrdersView {

	private Integer orderToDelete;
	
	@Inject
	OrderService orderService;

	public OrdersView() {
	}
	
	public void deleteOrder() {
		orderService.delete(orderToDelete);
	}
	
	
	public List<Order> getAll() {
		return orderService.allOrders();
	}


	// Getter und Setter
	public void setOrderToDelete(Integer orderToDelete) {
		this.orderToDelete = orderToDelete;
	}
	
}
