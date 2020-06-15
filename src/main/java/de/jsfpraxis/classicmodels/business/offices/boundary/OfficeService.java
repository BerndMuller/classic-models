package de.jsfpraxis.classicmodels.business.offices.boundary;

import javax.ejb.Stateful;

import de.jsfpraxis.classicmodels.business.EntityService;
import de.jsfpraxis.classicmodels.business.offices.entity.Office;

/**
 * Services (CRUD) for Offices. 
 * 
 * @author Bernd Müller
 *
 */
@SuppressWarnings("serial")
@Stateful
public class OfficeService extends EntityService<Office> {
	
	public OfficeService() {
		super(Office.class);
	}

}
