package database.implementations;
/**
 * 
 * @author Sven Noreillie
 *
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import common.*;
import database.DataService;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class Data<T extends ModelBase> implements DataService<T> {

	private List<T> internalList;
	private Class<T> classType;
	private DataReadWriteService<T> dataService;

	public Data(Class<T> classType) {
		this.classType = classType;
		dataService = DataSourceFactory.getSource(classType);
	}

	@Override
	public List<T> getAll() throws DBMissingException, DBException {
		if (this.internalList == null) {
			this.internalList = getListFromStream();
		}
		return this.internalList;
	}

	public List<T> getListFromStream() throws DBException, DBMissingException {
		return dataService.readDB();
	}

	@Override
	public T get(int id) throws NoSuchElementException, DBMissingException, DBException {
		List<T> entityList = this.getAll();
		return entityList.stream().filter(e -> e.getId() == id).findFirst().get();
	}

	@Override
	public List<T> getFiltered(Predicate<? super T> predicate)
			throws NoSuchElementException, DBMissingException, DBException {
		return this.getAll().stream().filter(predicate).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(ModelBase entity) throws DBMissingException, DBException {
		if (!this.classType.isAssignableFrom(entity.getClass()))
			throw new DBException("Wrong type in add");

		List<T> list = this.getAll();
		
		searchNextId(entity, list);

		if (!list.contains(entity)) {
			list.add((T) entity);
			this.internalList = list;
			dataService.writeDB(this.internalList);
		}
	}

	private void searchNextId(ModelBase entity, List<T> list) {
		if (entity.getId() == 0) {
			// Search next id
			Optional<T> possibleItem = list.stream().max(new Comparator<T>() {
				@Override
				public int compare(T o1, T o2) {
					return o1.getId() - o2.getId();
				}
			});

			if (possibleItem.isPresent()) {
				T item = possibleItem.get();
				entity.setId(item.getId() + 1);
			} else {
				entity.setId(1);
			}
		} else {
			if (list.contains(entity)) {
				list.remove(entity);
			}
		}
	}
	
	@Override
	public void addAll(List<? extends ModelBase> entities) throws DBMissingException, DBException {
		if (entities == null || entities.isEmpty()) throw new DBException("Entity list is null or empty");
		if (!this.classType.isAssignableFrom(entities.get(0).getClass()))
			throw new DBException("Wrong type in add");

		List<T> list = this.getAll();
		
		for (ModelBase entity : entities) {
			searchNextId(entity, list);
			if (!list.contains(entity)) {
				list.add((T) entity);
			}
		}
		
		this.internalList = list;
		dataService.writeDB(this.internalList);

	}

	@Override
	public void update(T entity) throws DBMissingException, DBException {
		if (entity.getId() == 0)
			throw new DBException("Id 0 found => update should have id to look for");

		List<T> list = this.getAll();
		if (list.contains(entity)) {
			list.remove(entity);
			list.add((T) entity);
			this.internalList = list;
			dataService.writeDB(this.internalList);
		}
	}

	@Override
	public void remove(T entity) throws DBMissingException, DBException {
		if (entity.getId() == 0)
			throw new DBException("Id 0 found => delete should have id to look for");
		List<T> list = this.getAll();
		if (list.contains(entity)) {
			list.remove(entity);
			dataService.writeDB(this.internalList);
		}
	}

	

}
