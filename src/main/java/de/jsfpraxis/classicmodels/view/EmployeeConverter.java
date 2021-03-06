package de.jsfpraxis.classicmodels.view;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.offices.boundary.EmployeeService;
import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

/**
 * Converter für Employees.
 * 
 * <p> Basiert auf der Eindeutigkeit von (firstName lastname). 
 * 
 * @author Bernd Müller
 *
 */
@SuppressWarnings("serial")
@Named
@ViewScoped
public class EmployeeConverter implements Converter<Employee>, Serializable {
	
	private Map<String, Employee> employees; // (firstName lastName) -> employee

	@Inject
	EmployeeService employeeService;
	
	@Override
	public Employee getAsObject(FacesContext context, UIComponent component, String value) {
		return value == null ? null : employees.get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Employee employee) {
		return employee == null ? null : employee.getFirstAndLastName();
	}
	
	@PostConstruct
	void init() {
		employees = employeeService.findAll().stream().collect(Collectors.toMap(employee -> employee.getFirstAndLastName(), employee -> employee));
	}
}
