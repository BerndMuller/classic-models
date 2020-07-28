package de.jsfpraxis.classicmodels.view;

import javax.faces.event.AjaxBehaviorEvent;
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
	private String customerName; // UI
	
	@NotNull
	private ProductLine productLine; // UI
	
	@NotNull
	private String productId; // UI
	
	@NotNull
	private Integer quantity; // UI
	
	@NotNull
	private BigDecimal price; // UI
	
	private List<OrderDetails> orderDetailsList; // gather all order items
	
	@Inject
	CustomerService customerService;
	
	@Inject
	OrderService orderService;
	
	@Inject
	ProductService productService;
	

	public OrderController() {
		orderDetailsList = new ArrayList<>();
	}

	
	public void addOderItem( ) {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setPosition(orderDetailsList.size() + 1);
		orderDetails.setQuantityOrdered(quantity);
		orderDetails.setPriceEach(price);
		orderDetails.setProduct(productService.find(productId));
		orderDetailsList.add(orderDetails);
		System.out.println("Warenkorb hinzugefügt: " + productId + " " + quantity + " " + price);
	}

	/**
	 * Action-Methode zum Speichern einer Bestellung.
	 * 
	 * @return Navigationsziel
	 */
	public String placeOrder() {
		Customer customer = customerService.findByName(customerName);
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setRequiredDate(LocalDate.now().plusDays(10));
		order.setStatus(OrderStatus.IN_PROCESS);
		order.setCustomer(customer);
		order.setOrderDetails(new ArrayList<>());
		for (OrderDetails orderDetails : orderDetailsList) {
			orderDetails.setOrder(order);
			order.getOrderDetails().add(orderDetails);
		}
		orderService.persist(order);
		return "order-details.xhtml?orderId=" + order.getId() + "&faces-redirect=true";
	}
	
	
	/**
	 * Ajax Listener für 'neues Produkt ausgewählt'. 
	 * 
	 * <p>
	 * Setzt die unverbindliche Preisempfehlung (MSRP Manufacturer's Suggested Retail Price).
	 * 
	 * @param event selection changed 
	 */
	public void productSelected(AjaxBehaviorEvent event) {
		price = productService.find(productId).getMsrp();
	}
	
	
	public List<Product> getProductsForSelectedProductLine() {
		return productService.findProductsForProductLine(productLine);
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

	public List<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}

}
