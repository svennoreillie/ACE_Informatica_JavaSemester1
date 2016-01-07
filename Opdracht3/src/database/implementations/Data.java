package database.implementations;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

	@SuppressWarnings("unchecked")
	@Override
	public void add(ModelBase entity) throws DBMissingException, DBException {
		if (!this.classType.isAssignableFrom(entity.getClass())) throw new DBException("Wrong type in add");
		List<T> tobList = this.getAll();
		if (!tobList.contains(entity)) {
			tobList.add((T)entity);
			dataService.writeDB(this.internalList);
		}
	}
	
	@Override
	public void update(T entity) throws DBMissingException, DBException {
		remove(entity);
		add(entity);
	}

	@Override
	public void remove(T entity) throws DBMissingException, DBException {
		List<T> tobList = this.getAll();
		if (tobList.contains(entity)) {
			tobList.remove(entity);
			dataService.writeDB(this.internalList);
		}
	}

	
}
