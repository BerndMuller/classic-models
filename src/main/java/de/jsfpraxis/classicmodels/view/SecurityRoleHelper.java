package de.jsfpraxis.classicmodels.view;

import java.security.Principal;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


/**
 * Hilfsklasse, damit in JSF-Views mit SecurityRole gearbeitet werden kann.
 * 
 * <p>
 * Verwendung von <f:importConstants> ist etwas schwieriger, da es Child von <f:view> sein muss.
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
	 * Rolle des eingeloggten Benutzers.
	 * 
	 * @return Rolle des eingeloggten Benutzers
	 */
	public SecurityRole getUserLoggedInRole() {
		if (isUserLoggedInAsAdmin()) {
			return SecurityRole.ADMIN;
		} else {
			return SecurityRole.EMPLOYEE;
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
	
	private Principal getUserPrincipal() {
		return ((HttpServletRequest) externalContext.getRequest()).getUserPrincipal();
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) externalContext.getRequest();
	}
}
