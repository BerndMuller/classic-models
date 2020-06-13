package de.jsfpraxis.classicmodels.business.accounting.boundary;

import javax.ejb.Stateful;

import de.jsfpraxis.classicmodels.business.EntityService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Order;

/**
 * Services (CRUD) for Orders. 
 * 
 * @author Bernd Müller
 *
 */
@SuppressWarnings("serial")
@Stateful
public class OrderService extends EntityService<Order> {

	public OrderService() {
		super(Order.class);
	}

}
