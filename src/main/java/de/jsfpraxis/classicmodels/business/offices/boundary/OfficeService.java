package de.jsfpraxis.classicmodels.business.offices.boundary;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.jsfpraxis.classicmodels.business.offices.entity.Office;

@Stateless
public class OfficeService {
	
	@PersistenceContext
	EntityManager em;
	

	public void create(Office office) {
		em.persist(office);
	}
	
	public Office read(Integer id) {
		return em.find(Office.class, id);
	}
	
	public Office update(Office office) {
		return em.merge(office);
	}
	
	public void delete(Integer id) {
		em.remove(em.find(Office.class, id));
	}
	
	public List<Office> allOffices() {
		return em.createNamedQuery("Office.getAll", Office.class).getResultList();
	}
	
	public Map<String, Office> allOfficesAsMap() {
		return em.createNamedQuery("Office.getAll", Office.class)
					.getResultStream()
					.collect(Collectors.toMap(office -> office.getCity() , office -> office));
	}

}
