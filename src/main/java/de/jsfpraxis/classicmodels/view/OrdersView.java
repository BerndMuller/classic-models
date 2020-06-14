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

	private List<Order> orders;
	
	@Inject
	OrderService orderService;

	
	public OrdersView() {
	}
	

	public int adjustPage(int pageNumber, int pageOffset) {
		return orderService.adjustPage(pageNumber, pageOffset);
	}

	
	public List<Order> getOrdersPaginated(int page) {
		if (orders == null) {
			orders = orderService.readPage(page);	
		}
		return orders; 
	}
	
	
	public List<Order> getOrdersForCustomer(Integer customerId) {
		return orderService.getOrdersForCustomer(customerId);
	}

	
}
