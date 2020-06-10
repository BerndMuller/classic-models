package de.jsfpraxis.classicmodels.business.accounting.boundary;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;

@Stateful
public class CustomerService {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	EntityManager em;
	
	
	public List<Customer> allCustomers() {
		return em.createNamedQuery("Customer.getAll", Customer.class).getResultList();
	}
}
