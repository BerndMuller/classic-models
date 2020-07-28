package de.jsfpraxis.classicmodels.business.offices.boundary;

import java.util.List;

import javax.ejb.Stateful;

import de.jsfpraxis.classicmodels.business.EntityService;
import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

/**
 * Services (CRUD) for Employees. 
 * 
 * @author Bernd Müller
 *
 */
@SuppressWarnings("serial")
@Stateful
public class EmployeeService extends EntityService<Employee> {

	public EmployeeService() {
		super(Employee.class);
	}

	public List<Employee> findAll() {
		return super.findAll();
	}
	
}
