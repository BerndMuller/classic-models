package de.jsfpraxis.classicmodels.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.offices.boundary.EmployeeService;
import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

@Named
@RequestScoped
public class EmployeesController {
	
	private Integer employeeToDelete;
	private List<Employee> employees;

	@Inject
	EmployeeService employeeService;
	
	public EmployeesController() {
	}
	
	public String deleteEmployee() {
		employeeService.remove(employeeToDelete);
		return "employees.xhtml?faces-redirect=true";
	}
	
	/**
	 * List of all employees.
	 * 
	 * <p> Prevents multiple JPA requests via lazy initialization and caching.
	 * 
	 * @return list of all employees.
	 */
	public List<Employee> getEmployees() {
		if (employees == null) {
			employees = employeeService.findAll();
		}
		return employees;
	}


	// Getter und Setter
	public void setEmployeeToDelete(Integer employeeToDelete) {
		this.employeeToDelete = employeeToDelete;
	}

	
}
