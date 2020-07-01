package de.jsfpraxis.classicmodels.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import de.jsfpraxis.classicmodels.business.products.boundary.ProductLineService;
import de.jsfpraxis.classicmodels.business.products.entity.ProductLine;


/**
 * JSF-Controller für Product-Line.
 * 
 * Request-Parameter-Übergabe mit @RequestParameterMap. 
 * 
 * @author Bernd Müller
 *
 */
@SuppressWarnings("serial")
@Named
@ViewScoped
public class ProductLineController implements Serializable {
	
	private static final int MAX_IMAGE_SIZE = 10000;
	
	private ProductLine productLine;
	
	private Part file; // hochzuladendes Bild
	
	@Inject
	FacesContext facesContext;
	
	@Inject
	ProductLineService productLineService;
	
	@Inject
	@RequestParameterMap
	Map<String, String> requestParams;

	
	public void validateFile(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Part file = (Part) value;
			if (file.getSize() > MAX_IMAGE_SIZE) {
				throw new ValidatorException(new FacesMessage("Bilddatei ist zu groß. Erlaubt max. " + MAX_IMAGE_SIZE + " Bytes"));
			}
		}
	}

	public ProductLineController() {
	}
	
	
	public String save() {
		if (productLine == null || productLine.getId() == null) {
			// neue Produktserien können generell nicht angelegt werden
		} else {
			if (file != null) {
				// Bild wurde hochgeladen
				try {
					productLine.setImage(file.getInputStream().readAllBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			productLineService.merge(productLine);
		}
		return "product-lines.xhtml?faces-redirect=true";
	}

	
	@PostConstruct
	void init() {
		if (requestParams.get("productLineId") != null) {
			productLine = productLineService.find(requestParams.get("productLineId"));	
		} else {
			NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
			navigationHandler.handleNavigation(facesContext, null, "/products.xhtml?faces-redirect=true");
		}
	}
	
	// Getter und Setter
	public ProductLine getProductLine() {
		return productLine;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
}
