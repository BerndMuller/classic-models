package de.jsfpraxis.classicmodels.business.accounting.boundary;

import javax.ejb.Stateful;

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
}
