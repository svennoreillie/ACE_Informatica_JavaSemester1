package database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import common.DBException;
import common.DBMissingException;
import database.implementations.Data;
import model.ModelBase;

public final class DataStrategy<T extends ModelBase> implements DataService<T> {

	private Class<T> classType;
	private static Map<Class<? extends ModelBase>, DataService<? extends ModelBase>> map;
	
	
	private DataStrategy(Class<T> classType) {
		this.classType = classType;
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T extends ModelBase> DataService<T> getDataService(Class<T> classType) {
		Data<T> dataImplementation = null;
		try {
			
			if (map == null)
				map = new HashMap<Class<? extends ModelBase>, DataService<? extends ModelBase>>();
			if (map.containsKey(classType)) {
				return (DataService<T>)map.get(classType);
			}

			dataImplementation = new Data<T>(classType);
			map.put(classType, dataImplementation);

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataImplementation;
	}

	
	@SuppressWarnings("unchecked")
	protected DataService<T> getService() throws DBMissingException {
		if (map.containsKey(this.classType)) {
			return (DataService<T>)map.get(this.classType);
		}
		throw new DBMissingException("DataService niet gevonden");
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
