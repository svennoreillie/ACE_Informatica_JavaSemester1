package database.internalInterface;

import java.util.List;

import common.DBException;
import common.DBMissingException;
import model.ModelBase;

public interface DataReadWriteService<T extends ModelBase> {
	List<T> readDB() throws DBMissingException, DBException;
	void writeDB(List<T> list) throws DBMissingException, DBException;
}
