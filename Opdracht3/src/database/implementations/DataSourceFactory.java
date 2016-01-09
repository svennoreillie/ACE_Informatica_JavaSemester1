package database.implementations;

/**
 * 
 * @author Sven Noreillie & Vervoort Peter
 *
 */

import database.helpers.DataSource;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class DataSourceFactory {
	private static DataSource _type = DataSource.Excel;

	public static void setType(DataSource type) {
		_type = type;
	}

	public static <T extends ModelBase> DataReadWriteService<T> getSource(Class<T> classType) {
		switch (_type) {
		case Text:
			return new DatabaseText<T>(classType);
		case SQL:
			return new DatabaseSQL<T>(classType);
		case Excel:
			return new DatabaseExcel<T>(classType);
		}
		
		return new DatabaseText<T>(classType);
	}

}
