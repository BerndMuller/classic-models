package de.jsfpraxis.classicmodels.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.accounting.boundary.OrderService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Order;

@Named
@RequestScoped
public class OrdersController {

	private List<Order> orders;
	
	@Inject
	OrderService orderService;

	public OrdersController() {
	}
	
	/**
	 * Adds the page offset to the current page number. 
	 * 
	 * @param pageNumber Current page number that should be adjusted (between 1 and actual amount of pages)
	 * @param pageOffset Offset to adjust page (positve increases, negative decreases)
	 * @return Modified page number
	 */
	public int adjustPage(int pageNumber, int pageOffset) {
		return orderService.adjustPage(pageNumber, pageOffset);
	}

	/**
	 * List of orders for page.
	 * 
	 * @param page Current page number
	 * @return List of orders for page
	 */
	public List<Order> getOrdersPaginated(int page) {
		if (orders == null) {
			orders = orderService.readPage(page);	
		}
		return orders; 
	}
	
	/**
	 * Orders for single customer.
	 * 
	 * @param customerId Id of customer
	 * @return List of orders of this customer
	 */
	public List<Order> getOrdersForCustomer(Integer customerId) {
		return orderService.getOrdersForCustomer(customerId);
	}
	
}
