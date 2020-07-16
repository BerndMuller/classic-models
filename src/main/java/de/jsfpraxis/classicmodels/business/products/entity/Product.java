package de.jsfpraxis.classicmodels.business.products.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Classic Models Products.
 * 
 * <p>
 * 
 * @author Bernd Müller
 *
 */
@Entity
@Table(name = "PRODUCTS")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p LEFT JOIN FETCH p.productLine")
@NamedQuery(name = "Product.findProductsForProductLine", query = "SELECT p FROM Product p where p.productLine = :productLine")
@NamedQuery(name = "Product.idsForScale", query = "SELECT p.id FROM Product p where p.id like :scale")
public class Product {

	@Id
	@Column(name = "ProductCode", length = 15)
	private String id;
	
	@NotNull
	private String productName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProductLine", nullable = false) // TODO length = 50
	private ProductLine productLine;

	@NotNull
	private String productScale;
	
	@NotNull
	private String productVendor;

	@NotNull
	@Column(length = 1000)
	private String productDescription;
	
	@NotNull
	private Integer quantityInStock;

	@NotNull
	private BigDecimal buyPrice;
	
	@NotNull
	private BigDecimal msrp;
	
	
	public Product() {
	}

	
	
    public Product(@NotNull String productName, ProductLine productLine, @NotNull String productScale, @NotNull String productVendor,
			@NotNull String productDescription, @NotNull Integer quantityInStock, @NotNull BigDecimal buyPrice, @NotNull BigDecimal msrp) {
			this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.msrp = msrp;
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
