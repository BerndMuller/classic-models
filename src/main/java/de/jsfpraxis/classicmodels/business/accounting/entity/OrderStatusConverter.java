package de.jsfpraxis.classicmodels.business.accounting.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

	@Override
	public String convertToDatabaseColumn(OrderStatus status) {
		return status.getText();
	}

	@Override
	public OrderStatus convertToEntityAttribute(String text) {
		return OrderStatus.fromText(text);
	}
	
}
