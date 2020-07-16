package de.jsfpraxis.classicmodels.business.products.boundary;

import java.util.List;
import java.util.OptionalInt;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.jsfpraxis.classicmodels.business.products.entity.Product;
import de.jsfpraxis.classicmodels.business.products.entity.ProductLine;

@RequestScoped
public class ProductService {

	@PersistenceContext
	EntityManager em;

	public ProductService() {
	}
	
	public Product find(String id) {
		return em.find(Product.class, id);
	}
	
	@Transactional
	public void persist(Product product) {
		product.setId(nextId(product.getProductScale()));
		em.persist(product);
	}
	
	
	/**
	 * Nächste Id für Product-Instanz.
	 * 
	 * <p>
	 * Das Entity Product benutzt einen String-Primärschlüssel der Form {@literal 'S<scale>_<integer>'}, leider in den
	 * Beispielen nicht ganz konsequent. Das {@literal <scale>} hier ist der Integer rechts vom Doppelpunkt, der
	 * Parameter der Methode ist ein Scale, wie im Property 'productScale'.
	 * 
	 * 
	 * @param scale der Maßstab im Format 1:N
	 * @return eine Id für diesen Maßstab
	 */
	public String nextId(String scale) {
		TypedQuery<String> query = em.createNamedQuery("Product.idsForScale", String.class);
		String scaleTo = scale.split(":")[1]; // Integer rechts vom Doppelpunkt
		query.setParameter("scale", "S"+ scaleTo + "_%");
		OptionalInt max = query.getResultStream().map(id -> id.split("_")[1]).mapToInt(Integer::valueOf).max();
		return "S"+ scaleTo + "_" + max.orElse(1);
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
