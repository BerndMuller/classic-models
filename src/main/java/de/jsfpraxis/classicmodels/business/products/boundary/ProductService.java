package de.jsfpraxis.classicmodels.business.products.boundary;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.jsfpraxis.classicmodels.business.products.entity.Product;
import de.jsfpraxis.classicmodels.business.products.entity.ProductLine;

@RequestScoped
public class ProductService {

	@PersistenceContext
	EntityManager em;

	public ProductService() {
	}
	
	/**
	 * All Products.
	 * 
	 * @return List of Product entities
	 */
	public List<Product> findAll() {
		return em.createNamedQuery("Product.findAll", Product.class).getResultList();	
	}
	
	
	
	public List<Product> findProductsForProductLine(ProductLine productLine) {
		TypedQuery<Product> query = em.createNamedQuery("Product.findProductsForProductLine", Product.class);
		query.setParameter("productLine", productLine);
		return query.getResultList();
	}
}
