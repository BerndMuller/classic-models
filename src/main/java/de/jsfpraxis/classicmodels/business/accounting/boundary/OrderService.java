package de.jsfpraxis.classicmodels.business.accounting.boundary;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.jsfpraxis.classicmodels.business.accounting.entity.Order;

@Stateful
public class OrderService {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	EntityManager em;
	
	public void create(Order order) {
		em.persist(order);
	}
	
	public Order read(Integer id) {
		return em.find(Order.class, id);
	}
	
	public Order update(Order order) {
		return order;// em.merge(order); TODO void ?
	}
	
	public void delete(Integer id) {
		em.remove(em.find(Order.class, id));
	}
	
	public List<Order> allOrders() {
		return em.createNamedQuery("Order.getAll", Order.class).getResultList();
	}
	
//	@PostConstruct
//	public void init() {
//		Logger.getLogger(OrderService.class.getCanonicalName()).info("created");
//	}
//	
//	@PreDestroy
//	public void cleanUp() {
//		Logger.getLogger(OrderService.class.getCanonicalName()).info("destroyed");
//	}

}
