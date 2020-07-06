package de.jsfpraxis.classicmodels.business.accounting.boundary;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.TypedQuery;

import de.jsfpraxis.classicmodels.business.EntityService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;

/**
 * Services (CRUD) for Customer. 
 * 
 * @author Bernd Müller
 *
 */
@SuppressWarnings("serial")
@Stateful
public class CustomerService extends EntityService<Customer> {

	public CustomerService() {
		super(Customer.class);
	}
	
	public Customer findByName(String customerName) {
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findByName", Customer.class);
		query.setParameter("customerName", customerName);
		return query.getSingleResult();
	}
	
	public List<String> getCustomerNames() {
		return em.createNamedQuery("Customer.names", String.class).getResultList();
	}
	
}
