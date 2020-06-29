package de.jsfpraxis.classicmodels.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.products.boundary.ProductService;
import de.jsfpraxis.classicmodels.business.products.entity.Product;

@Named
@RequestScoped
public class ProductsController {
	
	private List<Product> products;

	@Inject
	ProductService productService;
	
	public List<Product> getProducts() {
		if (products == null) {
			products = productService.findAll();
		}
		return products;
	}
	
}
