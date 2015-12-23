package database.implementations;

import database.helpers.DataSource;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class DataSourceFactory {
	private static DataSource _type = DataSource.EXCEL;

	public static void setType(DataSource type) {
		_type = type;
	}

	public static <T extends ModelBase> DataReadWriteService<T> getSource(Class<T> classType) {
		switch (_type) {
		case Text:
			return new DatabaseText<T>(classType);
		case SQL:
			return new DatabaseSQL<T>();
		case EXCEL:
			return new DatabaseExcel<T>(classType);
		}
		
		return new DatabaseText<T>(classType);
	}

}
