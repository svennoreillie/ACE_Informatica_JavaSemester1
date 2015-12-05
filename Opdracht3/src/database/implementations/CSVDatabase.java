package database.implementations;

import java.util.List;

import common.DBException;
import common.DBMissingException;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class CSVDatabase<T extends ModelBase> implements DataReadWriteService<T> {

	@Override
	public List<T> readDB() throws DBMissingException, DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeDB(List<T> list) throws DBMissingException, DBException {
		// TODO Auto-generated method stub
		
	}

}
