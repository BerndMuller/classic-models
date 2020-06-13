package de.jsfpraxis.classicmodels.business;

/**
 * For all entitites with Integer Id.
*/
public interface GenericEntity {

	/**
	 * Get the unique ID (primary key) of the object
	 *
	 * @return the entity Id
	 */
	Integer getId();

}
