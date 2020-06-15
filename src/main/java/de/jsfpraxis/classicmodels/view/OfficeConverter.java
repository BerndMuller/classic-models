package de.jsfpraxis.classicmodels.view;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.offices.boundary.OfficeService;
import de.jsfpraxis.classicmodels.business.offices.entity.Office;

/**
 * Converter für Offices.
 * 
 * <p>
 * Cached Offices, damit JPA/DB nur einmal angefragt wird. Basiert auf Eindeutigkeit der Städtenamen.
 * 
 * @author Bernd Müller
 *
 */
@SuppressWarnings("serial")
@Named
@ViewScoped
public class OfficeConverter implements Converter<Office>, Serializable {
	
	private Map<String, Office> offices; // city -> office

	@Inject
	OfficeService officeService;

	@Override
	public Office getAsObject(FacesContext context, UIComponent component, String value) {
		return offices.get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Office office) {
		return office == null ? null : office.getCity();
	}

	@PostConstruct
	void init() {
		offices = officeService.findAll().stream().collect(Collectors.toMap(office -> office.getCity(), office -> office));
	}
	
}
