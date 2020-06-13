package de.jsfpraxis.classicmodels.business;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

/**
 * Super class of all services working with entities.
 *
 * <p>
 * Entity classes should provide named queries of the form [type].[name],
 * where [name] is one of the String constants defined in this class.
 * 
 *
 * @param <T> the entity type of this service
 * 
 * @author Bernd Müller
 * 
 */
@SuppressWarnings("serial")
public abstract class EntityService<T> implements Serializable {
	
	private static final String COUNT = ".count"; 
	private static final String FIND_ALL = ".findAll";

	private Class<T> entityClass;
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	protected EntityManager em;
	
	@Inject
	Event<T> event;
	
    public EntityService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

	public void persist(T entity) {
		em.persist(entity);
		event.fire(entity);
	}

	public void remove(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
	}
	
	public void remove(Integer id) {
		em.remove(find(id));
	}

	public T find(Integer id) {
		return em.find(entityClass, id);
	}

	public void merge(T entity) {
		em.merge(entity);
	}

	
	public Integer count() {
		return Math.toIntExact(em.createNamedQuery(entityClass.getSimpleName() + COUNT, Long.class).getSingleResult());
	}
	
	
	/**
	 * All entites of type T. 
	 *
	 * @return List of all entities of type T
	 */
	public List<T> findAll() {
		return em.createNamedQuery(entityClass.getSimpleName() + FIND_ALL, entityClass).getResultList();	
	}

	/**
	 * All entites of type T paginated.
	 * 
	 * @param firstResult postion of first entity
	 * @param maxResult maximum number of entities
	 * @return List of all entities of type T starting at firstResult at most maxResults results.
	 */
	public List<T> findAll(int firstResult, int maxResult) {
		TypedQuery<T> query = em.createNamedQuery(entityClass.getSimpleName() + FIND_ALL, entityClass);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.getResultList();
	}
}
