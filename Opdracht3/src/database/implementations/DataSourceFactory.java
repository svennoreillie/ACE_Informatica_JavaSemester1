package database.implementations;

import database.helpers.DataSource;
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
		case SQL:
			return new SQLDatabase<T>();
		case EXCEL:
			return new ExcelDatabase<T>(classType);
		}
		
		return new TextDatabase<T>(classType);
	}

}
