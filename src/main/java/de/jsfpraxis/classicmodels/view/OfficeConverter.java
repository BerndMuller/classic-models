package de.jsfpraxis.classicmodels.view;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
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
@Named
@ApplicationScoped
public class OfficeConverter implements Converter<Office> {
	
	private Map<String, Office> offices; // city -> office

	@Inject
	OfficeService officeService;

	@Override
	public Office getAsObject(FacesContext context, UIComponent component, String value) {
		return offices.get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Office office) {
		return office.getCity();
	}

	public List<Office>  allOffices() {
		return officeService.allOffices();
	}
	
	@PostConstruct
	void init() {
		offices = officeService.allOfficesAsMap();
	}
	
}
