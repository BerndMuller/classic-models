package de.jsfpraxis.classicmodels.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.jsfpraxis.classicmodels.business.offices.boundary.OfficeService;
import de.jsfpraxis.classicmodels.business.offices.entity.Office;

@Named
@RequestScoped
public class OfficesView {

	@Inject
	OfficeService officeService;
	
	public List<Office> getAll() {
		return officeService.allOffices();
	}
	
}
