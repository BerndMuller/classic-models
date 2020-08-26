package de.jsfpraxis.classicmodels.view;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Test für /admin/employee.xhtml
 * 
 * @author bernd
 *
 */
@RunWith(Arquillian.class)
public class EmployeeIT {
	
	private static final String WEBAPP_SRC = "src/main/webapp";

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
				.addPackages(true, "de.jsfpraxis.classicmodels")
				.deleteClass(OrderDetailsController.class) // wg PDFBox-Abhängigkeit
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("import.sql")
				.merge(getWebAppArchive(), "/", Filters.includeAll())
				.addAsWebInfResource(new File("src/test/resources/WEB-INF/web.xml"), "web.xml")
				.addAsWebInfResource(new File("src/test/resources/WEB-INF/jboss-web.xml"), "jboss-web.xml");
	}
	
	
	private static GenericArchive getWebAppArchive() {
		return ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class).importDirectory(WEBAPP_SRC).as(GenericArchive.class);
	}

	@Drone
	private WebDriver browser;
	
	@ArquillianResource
	URL deploymentUrl;
	
	@Test
	//@RunAsClient
	public void newEmployee() {
	    browser.get(deploymentUrl.toExternalForm() + "admin/employee.xhtml");
	    WebElement firstName = browser.findElement(By.id("form:firstname"));
	    firstName.sendKeys("Mickey");
	    WebElement lastName = browser.findElement(By.id("form:lastname"));
	    lastName.sendKeys("Mouse");
	    WebElement email = browser.findElement(By.id("form:email"));
	    email.sendKeys("mickey@mouseton.com");
	    WebElement jobTitle = browser.findElement(By.id("form:jobtitle"));
	    jobTitle.sendKeys("Sales Rep");
	    WebElement extension = browser.findElement(By.id("form:extension"));
	    extension.sendKeys("x102");
	    Select office = new Select(browser.findElement(By.id("form:office")));
	    office.selectByValue("NYC");
	    Select reportsTo = new Select(browser.findElement(By.id("form:reportsto")));
	    reportsTo.selectByValue("Diane Murphy");
	    WebElement save = browser.findElement(By.id("form:save"));
	    Graphene.guardHttp(save).click();
	    Assert.assertTrue("no navigation to '/admin/employees.xhtm'", browser.getCurrentUrl().endsWith("/admin/employees.xhtml"));
	}

	
}
