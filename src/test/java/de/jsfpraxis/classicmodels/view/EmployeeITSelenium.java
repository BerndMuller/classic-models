package de.jsfpraxis.classicmodels.view;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Test für /admin/employee.xhtml.
 * 
 * @author bernd
 *
 */
@RunWith(Arquillian.class)
public class EmployeeITSelenium {
	
	private static final String WEBAPP_SRC = "src/main/webapp";
	
	private WebDriver driver;

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
				.addPackages(true, "de.jsfpraxis.classicmodels")
				.deleteClass(OrderDetailsController.class) // wg PDFBox-Abhängigkeit
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("import.sql")
				.merge(getWebAppArchive(), "/", Filters.includeAll())
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(new File("src/test/resources/WEB-INF/web.xml"), "web.xml")
				.addAsWebInfResource(new File("src/test/resources/WEB-INF/jboss-web.xml"), "jboss-web.xml");
	}
	
	
	private static GenericArchive getWebAppArchive() {
		return ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class).importDirectory(WEBAPP_SRC).as(GenericArchive.class);
	}

	
	@ArquillianResource
	URL deploymentUrl;
	
	
	@Test
	@RunAsClient
	public void newEmployee() {
	    driver.get(deploymentUrl.toExternalForm() + "admin/employee.xhtml");
	    WebElement firstName = driver.findElement(By.id("form:firstname"));
	    firstName.sendKeys("Mickey");
	    WebElement lastName = driver.findElement(By.id("form:lastname"));
	    lastName.sendKeys("Mouse");
	    WebElement email = driver.findElement(By.id("form:email"));
	    email.sendKeys("mickey@mouseton.com");
	    WebElement jobTitle = driver.findElement(By.id("form:jobtitle"));
	    jobTitle.sendKeys("Sales Rep");
	    WebElement extension = driver.findElement(By.id("form:extension"));
	    extension.sendKeys("x102");
	    Select office = new Select(driver.findElement(By.id("form:office")));
	    office.selectByValue("NYC");
	    Select reportsTo = new Select(driver.findElement(By.id("form:reportsto")));
	    reportsTo.selectByValue("Diane Murphy");
	    WebElement save = driver.findElement(By.id("form:save"));
	    save.click();
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.urlContains("/admin/employees.xhtml"));
	    //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("form:data")));
	    Assert.assertTrue("Mikey Mouse not added", driver.getPageSource().contains("mickey@mouseton.com"));
	}

	
	@Before
	public void before() {
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
	}

	@After
	public void after() {
		driver.close();
	}
	
}
