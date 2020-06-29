package de.jsfpraxis.classicmodels.business.products.boundary;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import de.jsfpraxis.classicmodels.business.products.entity.ProductLine;

@RequestScoped
public class ProductLineService {
	
	@PersistenceContext
	EntityManager em;
	
	public ProductLine find(String id) {
		return em.find(ProductLine.class, id);
	}
	
	@Transactional
	public void merge(ProductLine productLine) {
		em.merge(productLine);
	}
	
	/**
	 * All Product Lines.
	 * 
	 * @return List of Product Lines
	 */
	public List<ProductLine> findAll() {
		return em.createNamedQuery("ProductLine.findAll", ProductLine.class).getResultList();	
	}
}
