package de.jsfpraxis.classicmodels.business.offices.boundary;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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

	/**
	 * Demo für @RolesAllowed.
	 * <p>
	 * Um die javax.ejb.EJBAccessException zu erhalten, kopieren Sie die Datei /admin/employees.xhtml
	 * direkt unter "/". JSF/Sorteria erlaubt dann den Zugriff, die EJB nicht.
	 * 
	 */
	@RolesAllowed("ADMIN")
	public List<Employee> findAll() {
		return super.findAll();
	}
}
