package de.jsfpraxis.classicmodels.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.accounting.boundary.CustomerService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;

@Named
@RequestScoped
public class CustomerView {

	@Inject
	CustomerService customerService;
	
	public List<Customer> getAll() {
		return customerService.allCustomers();
	}
}
