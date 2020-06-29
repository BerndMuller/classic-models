package de.jsfpraxis.classicmodels.business.products.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ProductLines")
@NamedQuery(name = "ProductLine.findAll", query = "SELECT p from ProductLine p order by p.id")
public class ProductLine {

	@Id
	@Column(name = "ProductLine", length = 50)
	private String id;
	
	@Column(length = 4000)
	private String textDescription;
	
	@Column(length = 500)
	private String htmlDescription;
	
	@OneToMany(mappedBy = "productLine")
	private Set<Product> products;

	//@Lob // ist NICHT OID in Postgres
	private byte[] image;
	  
	
	public ProductLine() {
	}

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ProductLine)) {
        	return false;
        }
        return id != null && id.equals(((ProductLine) other).getId());
    }

    @Override
    public int hashCode() {
        return 42;
    }

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}




	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getTextDescription() {
		return textDescription;
	}


	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}


	public String getHtmlDescription() {
		return htmlDescription;
	}


	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}


	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}



	
	

}
