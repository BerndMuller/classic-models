package de.jsfpraxis.classicmodels.business.offices.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;

@Entity
@Table(name = "Employees")
@NamedQuery(name = "Employee.getAll", query = "SELECT e from Employee e LEFT JOIN FETCH e.office order by e.id")
@NamedQuery(name = "Employee.getNumberOfEmployees", query = "Select count(e) from Employee e")
@NamedQuery(name = "Employee.getByLastNameAndId", query = "SELECT e from Employee e where e.lastName = :lastName and e.id = :id")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EmployeeNumber")
	private Integer id;

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String extension;
	
	@NotNull
	private String email;
	  
	@ManyToOne
	@JoinColumn(name = "OfficeCode", nullable = false)
	private Office office;

	@ManyToOne
	@JoinColumn(name = "reportsTo", nullable = false)
	private Employee reportsTo;
	
	@Column(name = "JobTitle", length = 50, nullable = false)
	private String jobTitle;

	@OneToMany(mappedBy = "assignedEmployee")
	private Set<Customer> customers;
	
	
	public Employee() {
	}


	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object o) {
	    if(o instanceof Employee) {
	        Employee other = (Employee) o;
	        return id.equals(other.getId());
	    }
	    return false;
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String vorname) {
		this.firstName = vorname;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String nachname) {
		this.lastName = nachname;
	}

	public String getFirstAndLastName() {
		return firstName + " " + lastName;
	}
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}

	public Employee getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(Employee reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
}
