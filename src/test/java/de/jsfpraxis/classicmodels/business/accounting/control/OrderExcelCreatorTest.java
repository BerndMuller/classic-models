package de.jsfpraxis.classicmodels.business.accounting.control;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import de.jsfpraxis.classicmodels.business.accounting.entity.Order;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderDetails;
import de.jsfpraxis.classicmodels.business.products.entity.Product;
import de.jsfpraxis.classicmodels.business.products.entity.ProductLine;

public class OrderExcelCreatorTest {

	@Test
	public void orderTest() throws IOException {
		Order order = new Order();
		order.setId(4711);
		order.setOrderDetails(new ArrayList<>());
		order.setOrderDate(LocalDate.now());
		order.setRequiredDate(LocalDate.now().plusDays(10));
		
		OrderDetails details = new OrderDetails();
		details.setPosition(1);
		details.setProduct(new Product("Mini Cooper", new ProductLine(), "", "", "", 0, BigDecimal.ZERO, BigDecimal.ZERO));
		details.setPriceEach(new BigDecimal("9.95"));
		details.setQuantityOrdered(5);
		order.getOrderDetails().add(details);
		
		details = new OrderDetails();
		details.setPosition(2);
		details.setProduct(new Product("Harley-Davidson", new ProductLine(), "", "", "", 0, BigDecimal.ZERO, BigDecimal.ZERO));
		details.setPriceEach(new BigDecimal("11.95"));
		details.setQuantityOrdered(3);
		order.getOrderDetails().add(details);

		details = new OrderDetails();
		details.setPosition(3);
		details.setProduct(new Product("Isetta", new ProductLine(), "", "", "", 0, BigDecimal.ZERO, BigDecimal.ZERO));
		details.setPriceEach(new BigDecimal("12.95"));
		details.setQuantityOrdered(10);
		order.getOrderDetails().add(details);
		
		byte[] bytes = new OrderExcelCreator().toPdf(order);
		String tmpdir = System.getProperty("java.io.tmpdir");
		System.out.println("writing pdf test file to " + tmpdir);
		Files.write(Paths.get(tmpdir + "/order.xlsx"), bytes);
	}
	
}
