package de.jsfpraxis.classicmodels.business.offices.boundary;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

@Stateful
public class EmployeeService {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	EntityManager em;

	public EmployeeService() {
	}

	public void create(Employee employee) {
		em.persist(employee);
	}
	
	public Employee read(Integer id) {
		return em.find(Employee.class, id);
	}
	
//	public Employee update(Employee employee) {
//		//return em.merge(employee);
//		return employee;
//	}
	
	public void update(Employee employee) {
	}
	
	public void delete(Integer id) {
		em.remove(em.find(Employee.class, id));
	}
	
	public List<Employee> allEmployees() {
		return em.createNamedQuery("Employee.getAll", Employee.class).getResultList();
	}
	
	public Map<String, Employee> allEmployeesAsMap() {
		return em.createNamedQuery("Employee.getAll", Employee.class)
					.getResultStream()
					.collect(Collectors.toMap(employee -> employee.getFirstAndLastName(), employee -> employee));
	}
	
	@PostConstruct
	public void init() {
		Logger.getLogger(EmployeeService.class.getCanonicalName()).info("created");
	}
	
	@PreDestroy
	public void cleanUp() {
		Logger.getLogger(EmployeeService.class.getCanonicalName()).info("destroyed");
	}

}
