package de.jsfpraxis.classicmodels.view;

import java.security.Principal;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


/**
 * Hilfsklasse, damit die SecuritRole-Werte'ADMIN' und 'EMPLOYEE' als Typen und nicht als Strings in JSF-Views verwendet werden können.
 * 
 * @author Bernd Müller
 *
 */
@Named
@RequestScoped
public class SecurityRoleHelper {

	@Inject
	ExternalContext externalContext; 
	
	/**
	 * Rolle des eingeloggten Benutzers als lower-case String.
	 * 
	 * @return "admin" falls Rolle ADMIN, "employee" falls Rolle EMPLOYEE, "" sonst
	 */
	public String getUserLoggedInRole() {
		if (isUserLoggedInAsAdmin()) {
			return SecurityRole.ADMIN.toString().toLowerCase();
		} else if (isUserLoggedInAsEmployee()) {
			return SecurityRole.EMPLOYEE.toString().toLowerCase();
		} else {
			return "";
		}
	}
	
	public boolean isUserLoggedIn() {
		return getUserPrincipal() != null;
	}
	
	public boolean isUserLoggedInAsAdmin() {
		return getRequest().isUserInRole(SecurityRole.ADMIN.toString());
	}
	
	public boolean isUserLoggedInAsEmployee() {
		return getRequest().isUserInRole(SecurityRole.EMPLOYEE.toString());
	}

	public SecurityRole getAdmin() {
		return SecurityRole.ADMIN;
	}
	
	public SecurityRole getEmployee() { 
		return SecurityRole.EMPLOYEE;
	}
	
	public Principal getUserPrincipal() {
		return ((HttpServletRequest) externalContext.getRequest()).getUserPrincipal();
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) externalContext.getRequest();
	}
}
