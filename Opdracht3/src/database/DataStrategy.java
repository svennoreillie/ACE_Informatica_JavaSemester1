package database;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import common.DBException;
import common.DBMissingException;
import database.implementations.Data;
import model.ModelBase;

public class DataStrategy<T extends ModelBase> implements DataService<T> {

	private Class<T> classType;
	
	public DataStrategy(Class<T> classType) {
		this.classType = classType;
	}
	
	private Map<Class<T>, DataService<T>> map;
	
	protected DataService<T> getService() {
		Data<T> dataImplementation = null;
		try {
			
			if (map == null)
				map = new HashMap<Class<T>, DataService<T>>();
			if (map.containsKey(this.classType)) {
				return map.get(this.classType);
			}

			dataImplementation = new Data<T>(this.classType);
			map.put(this.classType, dataImplementation);

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataImplementation;
	}

	@Override
	public List<T> getAll() throws DBMissingException, DBException {
		DataService<T> service = getService();
		return service.getAll();
	}

	@Override
	public T get(int id) throws NoSuchElementException, DBMissingException, DBException {
		DataService<T> service = getService();
		return service.get(id);
	}

	@Override
	public void add(ModelBase entity) throws DBMissingException, DBException {
		DataService<T> service = getService();
		service.add(entity);
	}
	
	@Override
	public void update(T entity) throws DBMissingException, DBException {
		DataService<T> service = getService();
		service.update(entity);
	}

	@Override
	public void remove(T entity) throws DBMissingException, DBException {
		DataService<T> service = getService();
		service.remove(entity);
	}
}
