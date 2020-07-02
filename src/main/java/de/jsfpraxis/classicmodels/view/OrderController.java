package de.jsfpraxis.classicmodels.view;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import de.jsfpraxis.classicmodels.business.accounting.boundary.CustomerService;
import de.jsfpraxis.classicmodels.business.accounting.boundary.OrderService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;
import de.jsfpraxis.classicmodels.business.accounting.entity.Order;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderDetails;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderStatus;
import de.jsfpraxis.classicmodels.business.products.boundary.ProductService;
import de.jsfpraxis.classicmodels.business.products.entity.Product;
import de.jsfpraxis.classicmodels.business.products.entity.ProductLine;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class OrderController implements Serializable {
	
	@NotNull
	private String customerName;
	
	@NotNull
	private ProductLine productLine;
	
	private String productId;
	
	private Integer quantity;
	
	private BigDecimal price;
	
	private List<OrderDetails> details;
	
	
	@Inject
	CustomerService customerService;
	
	@Inject
	OrderService orderService;
	
	@Inject
	ProductService productService;
	

	public OrderController() {
		details = new ArrayList<>();
	}

	
	public void addOderItem( ) {
		System.out.println("customerName: " + customerName);
		System.out.println("productLine: " + productLine);
		System.out.println("product: " + productId);
		System.out.println("quantity: " + quantity);
		System.out.println("price: " + price);
		
		
		Customer customer = customerService.find(103);
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setRequiredDate(LocalDate.now().plusDays(10));
		order.setStatus(OrderStatus.IN_PROCESS);
		order.setCustomer(customer);
		orderService.persist(order);
		
		
		
		
	}
	
	public List<Product> getProductsForSelectedProductLine() {
		List<Product> products = productService.findProductsForProductLine(productLine);
		System.out.println("anzahl produkte " + products.size());
		return products;
	}
	
	public List<String> getCustomerNames() {
		return customerService.getCustomerNames();
	}
	
	// Getter und Setter
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public ProductLine getProductLine() {
		return productLine;
	}
	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}

	public String getProduct() {
		return productId;
	}
	public void setProduct(String productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
