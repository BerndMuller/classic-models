package de.jsfpraxis.classicmodels.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.products.boundary.ProductLineService;
import de.jsfpraxis.classicmodels.business.products.entity.ProductLine;

@Named
@RequestScoped
public class ProductLinesController {

	private List<ProductLine> productLines;

	@Inject
	ProductLineService productLineService;
	
	public List<ProductLine> getProductLines() {
		if (productLines == null) {
			productLines = productLineService.findAll();
		}
		return productLines;
	}
}
