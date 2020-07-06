package de.jsfpraxis.classicmodels.business.accounting.boundary;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.jsfpraxis.classicmodels.business.EntityService;
import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;
import de.jsfpraxis.classicmodels.business.accounting.entity.Order;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderDetails;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderStatus;
import de.jsfpraxis.classicmodels.business.products.boundary.ProductService;

@RunWith(Arquillian.class)
public class OrderServiceIT {

	private static final String S10_1678 = "S10_1678"; 
	private static final String S10_1949 = "S10_1949";
	private static final String S12_1666 = "S12_1666";
	
	@Inject
	CustomerService customerService;
	
	@Inject
	OrderService orderService;
	
	@Inject
	ProductService productService;
	
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
        		.addClass(EntityService.class)
        		.addPackages(true, "de.jsfpraxis.classicmodels.business.accounting")
        		.addPackages(true, "de.jsfpraxis.classicmodels.business.offices")
        		.addPackages(true, "de.jsfpraxis.classicmodels.business.products")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("import.sql")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Test
    public void getOrdersForCustomer() {
    	List<Order> orders = orderService.getOrdersForCustomer(103);
    	Assert.assertEquals(3, orders.size());
    }
    
    @Test
    public void placeOrder() {
    	List<OrderDetails> orderDetailsList = new ArrayList<>();
    	
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setPosition(orderDetailsList.size() + 1);
		orderDetails.setQuantityOrdered(1);
		orderDetails.setPriceEach(new BigDecimal("22.33"));
		orderDetails.setProduct(productService.find(S10_1678));
		orderDetailsList.add(orderDetails);

		orderDetails = new OrderDetails();
		orderDetails.setPosition(orderDetailsList.size() + 1);
		orderDetails.setQuantityOrdered(2);
		orderDetails.setPriceEach(new BigDecimal("33.44"));
		orderDetails.setProduct(productService.find(S10_1949));
		orderDetailsList.add(orderDetails);

		orderDetails = new OrderDetails();
		orderDetails.setPosition(orderDetailsList.size() + 1);
		orderDetails.setQuantityOrdered(3);
		orderDetails.setPriceEach(new BigDecimal("44.55"));
		orderDetails.setProduct(productService.find(S12_1666));
		orderDetailsList.add(orderDetails);
    	
		Customer customer = customerService.findByName("La Rochelle Gifts");
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setRequiredDate(LocalDate.now().plusDays(10));
		order.setStatus(OrderStatus.IN_PROCESS);
		order.setCustomer(customer);
		order.setOrderDetails(new ArrayList<>());
		for (OrderDetails details : orderDetailsList) {
			details.setOrder(order);
			order.getOrderDetails().add(details);
		}
		orderService.persist(order);
		
		Order savedOrder = orderService.find(order.getId());
		
		Assert.assertEquals(3, savedOrder.getOrderDetails().size());
    	
    }

}
