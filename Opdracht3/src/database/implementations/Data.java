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
import database.DataSourceFactory;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;


public class Data<T extends ModelBase> implements DataService<T> {

	private List<T> internalList;
	private DataReadWriteService<T> readWriteService;
	
	public Data() throws Exception {
		this.readWriteService = DataSourceFactory.GetService();
	}
	
	

	@Override
	public List<T> getAll() throws DBMissingException, DBException {
		if (this.internalList == null) {
			this.internalList = getListFromStream(); 
		}
		return this.internalList;
	}

	public List<T> getListFromStream() throws DBException, DBMissingException {
		return this.readWriteService.readDB();
	}

	@Override
	public T get(int id) throws NoSuchElementException, DBMissingException, DBException {
		List<T> entityList = this.getAll();
		return entityList.stream().filter(e -> e.getId() == id).findFirst().get();
	}

	@Override
	public void add(T entity) throws DBMissingException, DBException {
		List<T> tobList = this.getAll();
		if (!tobList.contains(entity)) {
			tobList.add(entity);
			this.readWriteService.writeDB(this.internalList);
		}
	}

	@Override
	public void remove(T entity) throws DBMissingException, DBException {
		List<T> tobList = this.getAll();
		if (tobList.contains(entity)) {
			tobList.remove(entity);
			this.readWriteService.writeDB(this.internalList);
		}
	}

	
}
