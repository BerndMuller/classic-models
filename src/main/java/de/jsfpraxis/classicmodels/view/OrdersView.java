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
	private List<Order> orders;
	
	@Inject
	OrderService orderService;

	public OrdersView() {
	}
	
	public void deleteOrder() {
		orderService.remove(orderToDelete);
	}
	
	public int adjustPage(int pageNumber, Integer pageOffset) {
		return orderService.adjustPage(pageNumber, pageOffset);
	}

	
	public List<Order> getOrders(int page) {
		if (orders == null) {
			orders = orderService.readPage(page);	
		}
		return orders; 
	}

	
	// Getter und Setter
	public void setOrderToDelete(Integer orderToDelete) {
		this.orderToDelete = orderToDelete;
	}
	
}
