package de.jsfpraxis.classicmodels.business.accounting.entity;

import org.junit.Assert;
import org.junit.Test;

public class OrderStatusTest {

	@Test
	public void conversionsOk() {
		for (OrderStatus orderStatus : OrderStatus.values()) {
			Assert.assertSame(orderStatus, OrderStatus.fromText(orderStatus.getText()));
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void conversationFails() {
		OrderStatus.fromText("something");
	}
}
