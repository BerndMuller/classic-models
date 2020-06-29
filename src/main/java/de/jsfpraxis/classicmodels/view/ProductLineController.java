package de.jsfpraxis.classicmodels.view;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

	private ProductLine productLine;
	
	@Inject
	ProductLineService productLineService;
	
	@Inject
	@RequestParameterMap
	Map<String, String> requestParams;


	public ProductLineController() {
	}
	
	public String save() {
		if (productLine.getId() == null) {
			// TODO
		} else {
			productLineService.merge(productLine);
		}
		return null;
	}
	
	@PostConstruct
	void init() {
		System.out.println("im postconstruct: " + requestParams.get("productLineId"));
		productLine = productLineService.find(requestParams.get("productLineId"));
	}
	
	// Getter und Setter
	public ProductLine getProductLine() {
		return productLine;
	}
	
}
