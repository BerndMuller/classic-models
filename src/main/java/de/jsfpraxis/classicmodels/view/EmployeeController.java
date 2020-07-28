package de.jsfpraxis.classicmodels.view;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.offices.boundary.EmployeeService;
import de.jsfpraxis.classicmodels.business.offices.boundary.OfficeService;
import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

@Named
@ViewScoped
public class EmployeeController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(EmployeeController.class.getCanonicalName());
	
	private Employee employee;
	private Integer employeeId; // View-Parameter
	
	@Inject
	EmployeeService employeeService;
	
	@Inject
	OfficeService officeService;
	
	public EmployeeController() {
	}

	
	public String save() {
		if (employee.getId() == null) {
			employeeService.persist(employee);
		} else {
			employeeService.merge(employee);
		}
		// TODO auf Seite bleiben ?
		return "employees.xhtml?faces-redirect=true";
	}
	
	
	/**
	 * JSF-View-Action, die Employee für View-Parameter lädt.
	 */
	public void viewAction() {
		if (employeeId == null) {
			// kein View-Parameter, also Neuanlage
			employee = new Employee();
		} else {
			employee = employeeService.find(employeeId);
		}
	}

	@PostConstruct
	public void init() {
		logger.info("created");
	}
	
	@PreDestroy
	public void cleanUp() {
		logger.info("to be destroyed");
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
