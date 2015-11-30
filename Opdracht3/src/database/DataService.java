package database;

import java.util.List;
import java.util.NoSuchElementException;

import common.DBException;
import common.DBMissingException;
import model.ModelBase;

public interface DataService<T extends ModelBase> {
	/**
	 * Fetches all items from a database without filtering 
	 * @return a List of all items specified by the generic type
	 * @throws DBMissingException
	 * @throws DBException
	 */
	List<T> getAll() throws DBMissingException, DBException;

	/**
	 * Fetch an item by id
	 * @param id
	 * @return a specific item that matches the given id if found
	 * @throws NoSuchElementException
	 * @throws DBMissingException
	 * @throws DBException
	 */
	T get(int id) throws NoSuchElementException, DBMissingException, DBException;

	/**
	 * Add an item to the database
	 * @param entity
	 * @throws DBMissingException
	 * @throws DBException
	 */
	void add(T entity) throws DBMissingException, DBException;

	/**
	 * Removes an item from the database
	 * @param entity
	 * @throws DBMissingException
	 * @throws DBException
	 */
	void remove(T entity) throws DBMissingException, DBException;
}
