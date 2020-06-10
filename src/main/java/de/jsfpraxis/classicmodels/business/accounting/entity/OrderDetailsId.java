package de.jsfpraxis.classicmodels.business.accounting.entity;

import java.io.Serializable;

public class OrderDetailsId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer order;
	private String product;
	
	
	public OrderDetailsId() {
	}

	public OrderDetailsId(Integer order, String product) {
		this.order = order;
		this.product = product;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((order == null) ? 0 : order.hashCode());
		result = prime * result
				+ ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsId other = (OrderDetailsId) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}


	public Integer getOrderId() {
		return order;
	}

	public String getProductId() {
		return product;
	}
	
}
