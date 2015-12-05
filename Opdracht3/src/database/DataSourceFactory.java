package database;

import database.helpers.DataSource;
import database.implementations.CSVDatabase;
import database.implementations.ExcelDatabase;
import database.implementations.SQLDatabase;
import database.implementations.TextDatabase;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class DataSourceFactory {
	private static DataSource _type = DataSource.Text;

	public static void setType(DataSource type) {
		_type = type;
	}

	public static <T extends ModelBase> DataReadWriteService<T> getSource(Class<T> classType) {
		switch (_type) {
		case Text:
			return new TextDatabase<T>(classType);
		case CSV:
			return new CSVDatabase<T>();
		case SQL:
			return new SQLDatabase<T>();
		case EXCEL:
			return new ExcelDatabase<T>();
		}
		
		return new TextDatabase<T>(classType);
	}

}
