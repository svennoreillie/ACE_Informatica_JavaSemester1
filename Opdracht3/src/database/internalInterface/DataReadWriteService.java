package database.internalInterface;

import java.io.IOException;
import java.util.List;

import common.DBException;
import common.DBMissingException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import model.ModelBase;

public interface DataReadWriteService<T extends ModelBase> {
	List<T> readDB() throws DBMissingException, DBException;
	void writeDB(List<T> list) throws DBMissingException, DBException;
}
