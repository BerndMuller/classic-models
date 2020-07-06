package de.jsfpraxis.classicmodels.business.accounting.boundary;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.TypedQuery;

import de.jsfpraxis.classicmodels.business.EntityService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Order;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderDetails;

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

	@Override
	public void persist(Order order) {
		for (OrderDetails orderDetails : order.getOrderDetails()) {
			em.merge(orderDetails.getProduct());
		}
		em.persist(order);
	}

	public List<Order> getOrdersForCustomer(Integer customerId) {
		TypedQuery<Order> query = em.createNamedQuery("Order.findOrdersForCustomer", Order.class);
		query.setParameter("customerId", customerId);
		return query.getResultList();
	}
}
