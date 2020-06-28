package de.jsfpraxis.classicmodels.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.accounting.boundary.CustomerService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;

@Named
@ViewScoped
public class CustomerController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	private Integer customerId; // View-Parameter
	
	@Inject
	CustomerService customerService;

	public CustomerController() {
		customer = new Customer();
	}

	
	public String save() {
		if (customer.getId() == null) {
			customerService.persist(customer);
		} else {
			customerService.merge(customer);
		}
		return "customers.xhtml?faces-redirect=true";
	}
	
	
	/**
	 * JSF-View-Action, die Cusotmer für View-Parameter lädt.
	 */
	public void viewAction() {
		if (customerId == null) {
			// kein View-Parameter, also Neuanlage
		} else {
			customer = customerService.find(customerId);
		}
	}

	// Getter und Setter
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}
