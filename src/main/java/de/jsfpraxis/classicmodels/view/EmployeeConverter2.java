package de.jsfpraxis.classicmodels.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.offices.boundary.EmployeeService;
import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

@FacesConverter(forClass = Employee.class)
public class EmployeeConverter2 implements Converter<Employee> {
	
	@Inject
	EmployeeService employeeService;
	
	@Override
	public Employee getAsObject(FacesContext context, UIComponent component, String value) {
		return value == null ? null : employeeService.findAll().stream().filter(e -> e.getFirstAndLastName().equals(value)).findFirst().get();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Employee employee) {
		return employee == null ? null : employee.getFirstAndLastName();
	}
	
//	void init() {
//		employees = employeeService.findAll().stream().collect(Collectors.toMap(employee -> employee.getFirstAndLastName(), employee -> employee));
//	}
}
