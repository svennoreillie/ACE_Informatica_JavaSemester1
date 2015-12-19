package database.implementations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import common.DBException;
import common.DBMissingException;
import database.DataStrategy;
import database.internalInterface.DataReadWriteService;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import model.ModelBase;

public class ExcelDatabase<T extends ModelBase> implements DataReadWriteService<T> {

	private final Class<T> classType;
	private final String className;
	private final File file;

	public ExcelDatabase(Class<T> classType) {
		this.classType = classType;
		this.className = classType.getName();
		this.file = new File(this.className + ".xlsx");
	}

	@Override
	public List<T> readDB() throws DBMissingException, DBException {
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(file);

			Sheet sheet = workbook.getSheet(0);
			int row = 0;

			ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
			while (row < sheet.getRows()) {
				int column = 0;
				ArrayList<String> rowinfo = new ArrayList<String>();
				while (column < sheet.getColumns()) {
					Cell cell = sheet.getCell(column, row);
					String information = cell.getContents();
					rowinfo.add(information);
					column++;
				}
				info.add(rowinfo);
				row++;
			}
			workbook.close();
		} catch (BiffException | IOException e) {
			throw new DBException("Error trying to read from Excel database", e);
		}
		return null;
	}

	@Override
	public void writeDB(List<T> list) throws DBMissingException, DBException {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(file);

			workbook.createSheet(className, 0);
			WritableSheet sheet = workbook.getSheet(0);

			List<Method> genericFieldArray = getFields();

			// Schrijf headers
			int col = 0;
			for (Method f : genericFieldArray) {
				Label label = new Label(col, 0, f.getName());
				sheet.addCell(label);
				col++;
			}

			// Schrijf data
			int row = 1;
			for (T item : list) {
				col = 0;

				for (Method f : genericFieldArray) {
					Object value;
					try {
						if (ModelBase.class.isAssignableFrom(f.getClass())) {
							//Deze field is een modelbase op zich
							//In dit geval moeten we de equivalent nemen
							DataStrategy strategy = new DataStrategy(f.getClass());
							ModelBase model = (ModelBase)f.invoke(item);
							strategy.add(model);
							//Save id of this item
							value = model.getId();
						} else {
							value = f.invoke(item);
						}
						
						if (value != null) {
							Label label = new Label(col, row, value.toString());
							sheet.addCell(label);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// stil afvangen
						throw new DBException("", e);
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						throw new DBException("", e);
					}

					col++;
				}

				row++;
			}

			workbook.write();
			workbook.close();
		} catch (IOException | WriteException e) {
			throw new DBException("Error writing to Excel database", e);
		}
	}

	private List<Method> getFields() {
		//Field[] genericFieldArray = this.classType.getDeclaredFields();
		
		List<Method> returnList = new ArrayList<Method>();
		for (Method m : this.classType.getMethods()) {
		    if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
		    	returnList.add(m);
		    }
		}
		
		
//		List<Field> returnList = new ArrayList<Field>();
//		for (Field field : genericFieldArray) {
//			if (Iterable.class.isAssignableFrom(field.getDeclaringClass())) continue;
//			returnList.add(field);
//		}
		return returnList;
	}

}
