package de.jsfpraxis.classicmodels.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.offices.boundary.EmployeeService;
import de.jsfpraxis.classicmodels.business.offices.boundary.OfficeService;
import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

@Named
@ViewScoped
public class EmployeeView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	private Integer employeeId; // View-Parameter
	
	@Inject
	EmployeeService employeeService;
	
	@Inject
	OfficeService officeService;
	
	public EmployeeView() {
		employee = new Employee();
	}

	
	public String save() {
		if (employee.getId() == null) {
			employeeService.create(employee);
		} else {
			System.out.println("in save() " + employee.getOffice().getCity());
			employeeService.update(employee);
		}
		return "employees.jsf?faces-redirect=true";
	}
	
	
	/**
	 * JSF-View-Action, die Employee für View-Parameter lädt.
	 */
	public void viewAction() {
		if (employeeId == null) {
			// kein View-Parameter, also Neuanlage
		} else {
			employee = employeeService.read(employeeId);
		}
	}

	
	// Getter und Setter
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

}
