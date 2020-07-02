package de.jsfpraxis.classicmodels.business.accounting.entity;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {

	CANCELLED("Cancelled"), DISPUTED("Disputed"), SHIPPED("Shipped"),
	IN_PROCESS("In Process"), ON_HOLD("On Hold"), RESOLVED("Resolved");
	
	private String text;
	
	private OrderStatus(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static OrderStatus fromText(String text) {
		Optional<OrderStatus> optional = Arrays.stream(OrderStatus.values()).filter(status -> status.getText().equals(text)).findAny();
		return optional.map(status -> status).orElseThrow(IllegalArgumentException::new);
	}
}
