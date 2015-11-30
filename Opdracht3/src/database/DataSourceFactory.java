package database;

import database.helpers.DataSource;
import database.implementations.TextDatabase;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class DataSourceFactory<T extends ModelBase> {
	private static DataSource _type = DataSource.Text;

	public static void setType(DataSource type) {
		_type = type;
	}

	public static <T extends ModelBase> DataReadWriteService<T> GetService() {
		switch (_type) {
		case Text:
			return new TextDatabase<T>();
		case CSV:

			break;
		case SQL:

			break;
		}
		
		return new TextDatabase<T>();
	}

}
