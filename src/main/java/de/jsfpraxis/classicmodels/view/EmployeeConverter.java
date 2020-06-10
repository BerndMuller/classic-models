package de.jsfpraxis.classicmodels.view;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.offices.boundary.EmployeeService;
import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

@Named
@ApplicationScoped
public class EmployeeConverter implements Converter<Employee> {
	
	private Map<String, Employee> employees; // (firstname lastname) -> employee

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
	
	public List<Employee>  allEmployees() {
		return employeeService.allEmployees();
	}
	
	@PostConstruct
	void init() {
		employees = employeeService.allEmployeesAsMap();
	}
}
