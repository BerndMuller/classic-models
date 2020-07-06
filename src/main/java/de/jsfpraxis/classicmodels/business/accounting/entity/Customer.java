package de.jsfpraxis.classicmodels.business.accounting.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import de.jsfpraxis.classicmodels.business.offices.entity.Employee;

@Entity
@Table(name = "Customers")
@NamedQuery(name = "Customer.findAll", query = "SELECT c from Customer c order by c.id")
@NamedQuery(name = "Customer.names", query = "SELECT c.customerName from Customer c order by c.customerName")
@NamedQuery(name = "Customer.findByName", query = "SELECT c from Customer c where customerName = :customerName")
@NamedQuery(name = "Customer.count", query = "Select count(c) from Customer c")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CustomerNumber")
	private Integer id;

	@NotNull
	@Column(length = 50, unique = true)
	private String customerName; // Firmenname
	
	@NotNull
	@Column(length = 50)
	private String contactFirstName;

	@NotNull	
	@Column(length = 50)
	private String contactLastName;

	@NotNull
	@Column(length = 50)
	private String phone;

	@NotNull	
	@Column(length = 50)
	private String addressLine1;
	
	@Column(length = 50)
	private String addressLine2;

	@NotNull	
	@Column(length = 50)
	private String city;
	
	@Column(length = 50)
	private String state;
	
	@Column(length = 15)
	private String postalCode;
	
	@Column(length = 50, nullable = false)
	private String country;

	@Column(nullable = false)
	private BigDecimal creditLimit; // in DB 'Decimal'

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SalesRepEmployeeNumber")
	private Employee assignedEmployee;

	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;

	public Customer() {
	}


	public Customer(String customerName, String contactFirstName,
			String contactLastName, String phone, String addressLine1,
			String addressLine2, String city, String state, String postalCode,
			String country, BigDecimal creditLimit) {
		super();
		this.customerName = customerName;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.creditLimit = creditLimit;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName
				+ ", contactFirstName=" + contactFirstName
				+ ", contactLastName=" + contactLastName + ", phone=" + phone
				+ ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country
				+ ", creditLimit=" + creditLimit + ", assignedEmployee="
				+ assignedEmployee + ", orders=" + orders + "]";
	}


	// Getter und Setter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public BigDecimal getCreditLimit() {
		return creditLimit;
	}


	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}


	public Employee getAssignedEmployee() {
		return assignedEmployee;
	}


	public void setAssignedEmployee(Employee assignedEmployee) {
		this.assignedEmployee = assignedEmployee;
	}


	public Set<Order> getOrders() {
		return orders;
	}


	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	


}
