package de.jsfpraxis.classicmodels.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import de.jsfpraxis.classicmodels.business.accounting.boundary.OrderService;
import de.jsfpraxis.classicmodels.business.accounting.control.OrderPdfCreator;
import de.jsfpraxis.classicmodels.business.accounting.entity.Order;
import de.jsfpraxis.classicmodels.business.accounting.entity.OrderDetails;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class OrderDetailsController implements Serializable {

	private Integer orderId; // view param
	
	private Order order;

	@Inject
	OrderService orderService;
	
	@Inject
	OrderPdfCreator orderPdfCreator;
	
	@Inject
	FacesContext facesContext;
	
	public OrderDetailsController() {
	}
	
	public void getOrderAsPdf() throws IOException {
		byte[] pdf = orderPdfCreator.toPdf(order);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(pdf.length);
		baos.write(pdf, 0, pdf.length);
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(pdf.length);
		response.setHeader("Content-disposition", "inline;filename=\"order-" + order.getId() + ".pdf\"");
		ServletOutputStream out = response.getOutputStream();
		baos.writeTo(out);
		out.flush();
		response.flushBuffer();
		facesContext.responseComplete();
	}

	public void viewAction() {
		order = orderService.find(orderId);
	}
	
	public String viewAction2() {
		if (orderId == null) {
			return "orders.xhtml?page=1&faces-redirect=true";	
		} else {
			order = orderService.find(orderId);
			return null;
		}
	}

	public List<OrderDetails> getOrderDetails() {
		if (orderId == null) {
			return List.of();
		} else {
			return order.getOrderDetails();	
		}
	}
	
	// Getter und Setter
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}
