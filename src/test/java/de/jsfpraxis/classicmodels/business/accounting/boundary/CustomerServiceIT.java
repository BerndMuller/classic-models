package de.jsfpraxis.classicmodels.business.accounting.boundary;

import javax.ejb.EJBException;
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
import de.jsfpraxis.classicmodels.business.GenericEntity;
import de.jsfpraxis.classicmodels.business.accounting.entity.Customer;

@RunWith(Arquillian.class)
public class CustomerServiceIT {
	
	@Inject
	CustomerService customerService;
	
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
        		.addClass(EntityService.class)
        		.addClass(GenericEntity.class)
        		.addPackages(true, "de.jsfpraxis.classicmodels.business.accounting")
        		.addPackages(true, "de.jsfpraxis.classicmodels.business.offices")
        		.addPackages(true, "de.jsfpraxis.classicmodels.business.products")
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("import.sql")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Test
    public void findByName() {
    	Customer customer = customerService.findByName("Atelier graphique");
    	Assert.assertEquals(Integer.valueOf(103), customer.getId());
    }

    @Test(expected = EJBException.class)
    public void findByNameNotFound() {
    	customerService.findByName(" gibt's nicht ");
    }

    
    
}
