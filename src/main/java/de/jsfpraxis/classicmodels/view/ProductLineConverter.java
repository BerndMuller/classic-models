package de.jsfpraxis.classicmodels.view;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.products.boundary.ProductLineService;
import de.jsfpraxis.classicmodels.business.products.entity.ProductLine;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ProductLineConverter implements Converter<ProductLine>, Serializable  {
	
	private Map<String, ProductLine> productLines;
	
	@Inject
	ProductLineService productLineService;
	
	@Override
	public ProductLine getAsObject(FacesContext context, UIComponent component, String value) {
		return productLines.get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, ProductLine pl) {
		return pl == null ? null : pl.getId();
	}
	
	public Collection<ProductLine> getProductLines() {
		return productLines.values();
	}
	
	@PostConstruct
	void init() {
		productLines = productLineService.findAll().stream().collect(Collectors.toMap(pl -> pl.getId(), pl -> pl));
	}
	
}
