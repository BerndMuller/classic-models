package de.jsfpraxis.classicmodels.business.offices.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Offices")
@NamedQuery(name = "Office.findAll", query = "SELECT o from Office o")
public class Office {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OfficeCode")
	private Integer id;

	@Column(length = 50, nullable = false)
	private String city;
	
	@Column(name = "Phone", length = 50, nullable = false)
	private String phone;
	  
	@Column(length = 50, nullable = false)
	private String addressLine1;
	
	@Column(length = 50)
	private String addressLine2;

	@Column(length = 50)
	private String state;
	
	@Column(length = 50, nullable = false)
	private String country;

	@Column(length = 15, nullable = false)
	private String postalCode;

	@Column(name = "Territory", length = 10, nullable = false)
	private String territory;
	
	@OneToMany(mappedBy = "office")
	private Set<Employee> employees;

	
	public Office() {
	}

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Office)) {
        	return false;
        }
        return id != null && id.equals(((Office) other).getId());
    }

    @Override
    public int hashCode() {
        return 42;
    }


	
	// Getter and Setter
	public Integer getId() {
		return id;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String telefon) {
		this.phone = telefon;
	}

	public String getAdressLine1() {
		return addressLine1;
	}
	public void setAdressLine1(String adressLine1) {
		this.addressLine1 = adressLine1;
	}

	public String getAdressLine2() {
		return addressLine2;
	}
	public void setAdressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTerritory() {
		return territory;
	}
	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
}
