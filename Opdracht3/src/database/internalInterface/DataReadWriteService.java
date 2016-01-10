package database.internalInterface;

/**
 * 
 * @author Sven Noreillie
 *
 */

import java.util.List;
import common.DBException;
import common.DBMissingException;
import model.ModelBase;

public interface DataReadWriteService<T extends ModelBase> {
	List<T> readDB() throws DBMissingException, DBException;
	void writeDB(List<T> list, Boolean update) throws DBMissingException, DBException;
}
