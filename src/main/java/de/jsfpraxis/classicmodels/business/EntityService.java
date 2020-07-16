package de.jsfpraxis.classicmodels.business;

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
	private static final int PAGE_SIZE = 20; // pagination size for query results

	private Class<T> entityClass;
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	protected EntityManager em;
	
    public EntityService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

	public void persist(T entity) {
		em.persist(entity);
	}

	public void remove(Integer id) {
		em.remove(find(id));
	}

	public T find(Integer id) {
		return em.find(entityClass, id);
	}

	public void merge(T entity) {
		// i.l.b.
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
	 * Reads entities for page.
	 * 
	 * Used to paginate through result sets.
	 *
	 * @param page The page number, starting with 1
	 * @return list of entities for given page number
	 */
	public List<T> readPage(int page) {
		if (page == 0) {
			page = 1;
		}
		TypedQuery<T> query = em.createNamedQuery(entityClass.getSimpleName() + FIND_ALL, entityClass);	
		query.setFirstResult((page - 1) * PAGE_SIZE);
		query.setMaxResults(PAGE_SIZE);
		return query.getResultList();
	}

	
	/**
	 * How many pages are available.
	 * 
	 * @return Number of pages available
	 */
	public int availablePages() {
		int numberOfEntitiesAvailable = em.createNamedQuery(entityClass.getSimpleName() + COUNT, Long.class).getSingleResult().intValue();
		if (numberOfEntitiesAvailable % PAGE_SIZE == 0) {
			return numberOfEntitiesAvailable / PAGE_SIZE;
		} else {
			return numberOfEntitiesAvailable / PAGE_SIZE + 1;
		}
	}

	
	/**
	 * Adds the page offset to the current page number. 
	 * 
	 * @param pageNumber Current page number that should be adjusted (between 1 and actual amount of pages)
	 * @param pageOffset Offset to adjust page (positve increases, negative decreases)
	 * @return Modified page number
	 */
	public int adjustPage(int pageNumber, int pageOffset) {
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		if (pageNumber + pageOffset > 0 && pageNumber + pageOffset <= availablePages()) {
			return pageNumber + pageOffset;
		} else {
			return pageNumber;
		}
	}

	
}
