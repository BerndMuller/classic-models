package de.jsfpraxis.classicmodels.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.accounting.boundary.CustomerService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;

@Named
@RequestScoped
public class CustomersController {
	
	private Integer customerToDelete;
	private List<Customer> customers;

	@Inject
	CustomerService customerService;
		
	public CustomersController() {
	}
	
	public void deleteEmployee() {
		customerService.remove(customerToDelete);
	}
	
	/**
	 * List of all customers.
	 * 
	 * <p> Prevents multiple JPA requests via lazy initialization and caching.
	 * 
	 * @return list of all customers.
	 */
	public List<Customer> getCustomers() {
		if (customers == null) {
			customers = customerService.findAll();
		}
		return customers;
	}

	
	// Getter und Setter
	public void setCustomerToDelete(Integer customerToDelete) {
		this.customerToDelete = customerToDelete;
	}
	
}
