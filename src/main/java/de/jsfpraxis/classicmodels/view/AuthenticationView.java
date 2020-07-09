package de.jsfpraxis.classicmodels.view;

import java.io.IOException;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import de.jsfpraxis.classicmodels.security.SessionManager;

@Named
@RequestScoped
public class AuthenticationView {
	
	private static final Logger logger = Logger.getLogger(AuthenticationView.class.getCanonicalName());

	@Inject
	SecurityContext securityContext;

	@Inject
	SessionManager sessionManager;

	@Inject
	ExternalContext externalContext;

	@Inject
	FacesContext facesContext;

	@NotNull
	private Integer userId; // maps to Employee.id

	@NotNull
	private String password; // maps to Employee.lastname

	public AuthenticationView() {

	}

	public void login() throws IOException {
		Credential credential = new UsernamePasswordCredential(String.valueOf(userId), new Password(password));
		AuthenticationStatus status = 
			securityContext.authenticate((HttpServletRequest) externalContext.getRequest(),
			                             (HttpServletResponse) externalContext.getResponse(),
			                             AuthenticationParameters.withParams().credential(credential));
		logger.info("authentication status: " + status);
		if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentifizierung fehlgeschlagen.", null));
		} else if (status.equals(AuthenticationStatus.SEND_CONTINUE) || status.equals(AuthenticationStatus.SUCCESS)) {
			sessionManager.replaceSessionForUser(userId, (HttpSession) externalContext.getSession(false));
			if (status.equals(AuthenticationStatus.SEND_CONTINUE)) {
				facesContext.responseComplete();
			} else {
				externalContext.redirect(externalContext.getApplicationContextPath() + "/home.xhtml");
			}
		}
		credential.clear(); // Passwort überschreiben
	}

	
	public String logout() throws ServletException {
		Integer userId = Integer.valueOf(securityContext.getCallerPrincipal().getName());
		logger.info("logout for user with Id " + userId + " requestet.");
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		request.logout();
		sessionManager.replaceSessionForUser(userId, null);
		HttpSession session = (HttpSession) externalContext.getSession(false);
		if (session != null) {
			try {
				session.invalidate();
			} catch (IllegalStateException e) {
				// Session already invalidated. Do nothing.
			}
		}
		return "/home.xhtml?faces-redirect=true";
	}
	

	// Getter and Setter
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
