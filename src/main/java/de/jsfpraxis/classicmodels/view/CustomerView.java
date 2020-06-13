package de.jsfpraxis.classicmodels.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.accounting.boundary.CustomerService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;
import de.jsfpraxis.classicmodels.business.offices.boundary.EmployeeService;
import de.jsfpraxis.classicmodels.business.offices.boundary.OfficeService;
import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

@Named
@ViewScoped
public class CustomerView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	private Integer customerId; // View-Parameter
	
	@Inject
	CustomerService customerService;

	public CustomerView() {
		customer = new Customer();
	}

	
	public String save() {
		if (customer.getId() == null) {
			//customerService.create(customer);
		} else {
			//customerService.update(customer);
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
			//customer = customerService.read(customerId);
		}
	}

	
	// Getter und Setter

}
