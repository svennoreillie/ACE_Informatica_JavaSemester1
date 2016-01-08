package database.implementations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.helpers.ReflectionPropertyHelper;
import database.internalInterface.DataReadWriteService;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import model.*;

public class DatabaseExcel<T extends ModelBase> extends ReflectionDatabase<T> implements DataReadWriteService<T> {

	private final String className;
	private final File file;

	public DatabaseExcel(Class<T> classType) {
		super(classType);
		this.className = classType.getName();
		this.file = new File(this.className + ".xls");
	}

	@Override
	public List<T> readDB() throws DBMissingException, DBException {
		Workbook workbook = null;
		try {
			List<T> returnList = new ArrayList<T>();

			// check if file exists
			if (!this.file.exists() && !this.file.isDirectory()) {
				return returnList;
			}

			// open excel
			workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);

			// load the structure of T & prepare returning list
			List<ReflectionPropertyHelper> genericFieldArray = getFields();

			int row = 1;
			while (row < sheet.getRows()) {
				// create an instance of T for this row
				T instance = this.classType.newInstance();

				int column = 0;
				while (column < sheet.getColumns()) {
					try {
						// read cell contents
						Cell cell = sheet.getCell(column, row);
						String content = cell.getContents();

						// get setter for this column
						ReflectionPropertyHelper property = genericFieldArray.get(column);
						Method set = property.getSetter();

						// cast string to propertytype & invoke

						switch (property.getPropertyType().getName()) {
						case "java.lang.String":
							set.invoke(instance, content);
							break;
						case "int":
							set.invoke(instance, Integer.parseInt(content));
							break;
						case "boolean":
							set.invoke(instance, Boolean.parseBoolean(content));
							break;
						case "java.math.BigDecimal":
							set.invoke(instance, new BigDecimal(content));
                            break;
						case "java.lang.Double":
							set.invoke(instance, Double.parseDouble(content));
                            break;
						case "org.joda.time.DateTime":
							set.invoke(instance, org.joda.time.DateTime.parse(content));
							break;
						default:
							if (ModelBase.class.isAssignableFrom(property.getPropertyType())) {
								if (Modifier.isAbstract( property.getPropertyType().getModifiers() )) {
									String[] splittedContent = content.split(" ");
									String classType = splittedContent[0];
									int id = Integer.parseInt(splittedContent[1]);
									
									DataService<? extends ModelBase> strategy = GetDedicatedDataService(classType);
									ModelBase reference = strategy.get(id);
									set.invoke(instance, property.getPropertyType().cast(reference));

								} else {
									// get from dedicated dataservice
									DataService<? extends ModelBase> strategy = GetDedicatedDataService(
											property.getPropertyType().getName());
									ModelBase reference = strategy.get(Integer.parseInt(content));
									set.invoke(instance, property.getPropertyType().cast(reference));
								}
							}
							break;
						}

					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// skip this information but log it
						System.out.println(e.toString());
					}

					column++;
				}
				// store the new instance in a temp list
				returnList.add(instance);

				row++;
			}

			return returnList;

		} catch (IOException e) {
			throw new DBMissingException("Excel not found");
		} catch (BiffException e) {
			//Clear excel file and return null
			this.file.delete();
			return null;
		} catch (InstantiationException | IllegalAccessException e1) {
			throw new DBException("Cannot instantiate the generic T " + this.className, e1);
		} finally {
			if (workbook != null)
				workbook.close();
		}
	}

	@Override
	public void writeDB(List<T> list) throws DBMissingException, DBException {
		WritableWorkbook workbook = null;
		try {
			// create excel
			workbook = Workbook.createWorkbook(file);
			workbook.createSheet(className, 0);
			WritableSheet sheet = workbook.getSheet(0);

			// load structure of T
			List<ReflectionPropertyHelper> genericFieldArray = getFields();

			// write header row
			int col = 0;
			for (ReflectionPropertyHelper f : genericFieldArray) {
				Label label = new Label(col, 0, f.getName());
				sheet.addCell(label);
				col++;
			}

			// write data
			int row = 1;
			for (T item : list) {
				// new row for every item in the list
				col = 0;
				for (ReflectionPropertyHelper property : genericFieldArray) {
					// check the value of every property
					Object value;
					try {
						if (ModelBase.class.isAssignableFrom(property.getPropertyType())) {
							// found property is a ModelBase => lookup id to
							// write in this excel
							// leave storing of the found ModelBase to an
							// instance of ExcelDatabase with type of found
							// ModelBase


							// get the value of this property for the current
							// item in the list & save to other DataService
							ModelBase model = (ModelBase) property.getGetter().invoke(item);

								// ugly helper method to create a DataService, could
								// not find a way to do it generic
								DataService<? extends ModelBase> strategy = GetDedicatedDataService(model.getClass().getName());

								strategy.add(model);
								// save id of this item in our own excel
								value = model.getId();
								if (Modifier.isAbstract( property.getPropertyType().getModifiers() )) {
									//Could be the abstract item class => prefix with classtype
									value = model.getClass().getName() + " " + value;
								} 
							
						} else {
							// normal property, get the object for this item in
							// the list
							value = property.getGetter().invoke(item);
						}

						if (value != null) {
							// fill the cell with the string representation of
							// the found object
							Label label = new Label(col, row, value.toString());
							sheet.addCell(label);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO :: logging
					} catch (InvocationTargetException e) {
						// error invoking the getter => just continue
						// TODO:: logging
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

}
