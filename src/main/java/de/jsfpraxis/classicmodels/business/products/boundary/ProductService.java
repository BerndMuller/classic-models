package de.jsfpraxis.classicmodels.business.products.boundary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.jsfpraxis.classicmodels.business.products.entity.Product;

public class ProductService {

	@PersistenceContext
	EntityManager em;

	public ProductService() {
	}
	
	/**
	 * All Product entities.
	 * 
	 * @return List of Product entities
	 */
	public List<Product> findAll() {
		return em.createNamedQuery("Product.findAll", Product.class).getResultList();	
	}
}
