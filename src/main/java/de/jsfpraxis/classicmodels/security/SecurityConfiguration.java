package de.jsfpraxis.classicmodels.security;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;


/**
 * 
 * @author Bernd Müller
 *
 */
@CustomFormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(loginPage = "/login.xhtml", useForwardToLogin = false, errorPage = ""))

@DatabaseIdentityStoreDefinition(
		dataSourceLookup = "java:comp/DefaultDataSource", 
		callerQuery = "SELECT lastname FROM employees WHERE employeenumber = ?",
		groupsQuery = "SELECT CASE WHEN employeenumber = 1002 THEN 'ADMIN' ELSE 'EMPLOYEE' END AS ROLE FROM employees WHERE employeenumber = ?",
		hashAlgorithm = SimpleHash.class) 

@ApplicationScoped
public class SecurityConfiguration {

	// TODO hier können z.B. Parameter des Hash-Algorithmus gespeichert werden

}
