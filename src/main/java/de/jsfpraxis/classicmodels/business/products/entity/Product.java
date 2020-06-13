package de.jsfpraxis.classicmodels.business.products.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
@NamedQueries(
	@NamedQuery(name = "Product.getAll", query = "SELECT p from Product p")
)
public class Product {

	@Id
	@Column(name = "ProductCode", length = 15)
	private String id;
	
	@Column(length = 70, nullable = false)
	private String productName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductLine", nullable = false) // TODO length = 50
	private ProductLine productLine;

	@Column(length = 10, nullable = false)
	private String productScale;
	
	@Column(length = 50, nullable = false)
	private String productVendor;

	@Column(length = 1000, nullable = false)
	private String productDescription;
	
	@Column(nullable = false)
	private Integer quantityInStock;

	@Column(nullable = false)
	private BigDecimal buyPrice;
	
	@Column(name = "MSRP", nullable = false)
	private BigDecimal msrp;
	
	
	
	public Product() {
	}

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Product)) {
        	return false;
        }
        return id != null && id.equals(((Product) other).getId());
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


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public ProductLine getProductLine() {
		return productLine;
	}


	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}


	public String getProductScale() {
		return productScale;
	}


	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}


	public String getProductVendor() {
		return productVendor;
	}


	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public Integer getQuantityInStock() {
		return quantityInStock;
	}


	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}


	public BigDecimal getBuyPrice() {
		return buyPrice;
	}


	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}


	public BigDecimal getMsrp() {
		return msrp;
	}


	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}



	
}
