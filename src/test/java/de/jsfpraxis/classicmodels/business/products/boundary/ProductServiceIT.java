package de.jsfpraxis.classicmodels.business.products.boundary;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.jsfpraxis.classicmodels.business.EntityService;
import de.jsfpraxis.classicmodels.business.products.entity.Product;
import de.jsfpraxis.classicmodels.business.products.entity.ProductLine;

@RunWith(Arquillian.class)
public class ProductServiceIT {
	
	private static final String SPECIAL_PRODUCT_NAME = "very special product name";
	
	@Inject
	ProductService productService;
	
	@Inject
	ProductLineService productLineService;
	
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
        		.addClass(EntityService.class)
        		.addPackages(true, "de.jsfpraxis.classicmodels.business.accounting")
        		.addPackages(true, "de.jsfpraxis.classicmodels.business.offices")
        		.addPackages(true, "de.jsfpraxis.classicmodels.business.products")
        		.addPackages(false, "de.jsfpraxis.classicmodels.business")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("import.sql")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    
    @Test
    public void numberOfProducts() {
    	List<Product> allProducts = productService.findAll();
    	Assert.assertSame(110, allProducts.size());
	}

    
    @Test
    public void persistProduct() {
    	ProductLine productLine = new ProductLine();
    	productLine.setId("Vintage Cars");
    	Product product = new Product(SPECIAL_PRODUCT_NAME, productLine, "1:20", "vendor", "description", 22, new BigDecimal("3.33"), new BigDecimal("4.44"));
    	productService.persist(product);
    	Assert.assertTrue("Neues Produkt nicht gespeichert", 
    			productService.findAll().stream().filter(p -> p.getProductName().equals(SPECIAL_PRODUCT_NAME)).count() == 1); 
    }

    
}
