package database.implementations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.DataStrategy;
import database.helpers.ReflectionPropertyHelper;
import database.internalInterface.DataReadWriteService;
import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import model.subItems.*;
import model.*;

public class DatabaseExcel<T extends ModelBase> implements DataReadWriteService<T> {

	private final Class<T> classType;
	private final String className;
	private final File file;

	public DatabaseExcel(Class<T> classType) {
		this.classType = classType;
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
						default:
							if (ModelBase.class.isAssignableFrom(property.getPropertyType())) {
								// get from dedicated dataservice
								DataService<? extends ModelBase> strategy = GetDedicatedDataService(
										property.getPropertyType().getName());
								ModelBase reference = strategy.get(Integer.parseInt(content));
								set.invoke(instance, property.getPropertyType().cast(reference));
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
			throw new DBException("Error trying to read from Excel database", e);
		} catch (InstantiationException | IllegalAccessException e1) {
			throw new DBException("Cannot instantiate the generic T", e1);
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
							String classTypeString = property.getPropertyType().getName();
							// ugly helper method to create a DataService, could
							// not find a way to do it generic
							DataService<? extends ModelBase> strategy = GetDedicatedDataService(classTypeString);

							// get the value of this property for the current
							// item in the list & save to other DataService
							ModelBase model = (ModelBase) property.getGetter().invoke(item);
							strategy.add(model);
							// save id of this item in our own excel
							value = model.getId();
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

	private DataService<? extends ModelBase> GetDedicatedDataService(String classTypeString) {
		// ugly helper to instantiate a new DataService of the correct type
		// might be a better solution for it (without converting all of it to
		// C#)
		switch (classTypeString) {
		case "model.Person":
			return new DataStrategy<Person>(Person.class);
		case "model.Address":
			return new DataStrategy<Address>(Address.class);
		case "model.Customer":
			return new DataStrategy<Customer>(Customer.class);
		case "model.Item":
			return new DataStrategy<Item>(Item.class);
		case "model.Shop":
			return new DataStrategy<Shop>(Shop.class);
		case "model.Uitlening":
			return new DataStrategy<Uitlening>(Uitlening.class);
		case "model.subItems.Cd":
			return new DataStrategy<Cd>(Cd.class);
		case "model.subItems.Dvd":
			return new DataStrategy<Dvd>(Dvd.class);
		case "model.subItems.Game":
			return new DataStrategy<Game>(Game.class);
		default:
			return null;
		}
	}

	private List<ReflectionPropertyHelper> properties;

	private List<ReflectionPropertyHelper> getFields() {
		if (properties == null) {

			properties = new ArrayList<ReflectionPropertyHelper>();

			// iterate all methods found in T
			for (Method m : this.classType.getMethods()) {
				if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
					// if the name starts with get and has no parameters we
					// assume it is a getter
					ReflectionPropertyHelper helper = new ReflectionPropertyHelper();
					try {
						String getName = m.getName();
						helper.setName(getName.substring(3, getName.length()));
						helper.setGetter(m);
						helper.setPropertyType(m.getReturnType());

						// look for a setter
						if (helper.getName().equals("Class"))
							continue;
						Method setter = this.classType.getMethod("set" + helper.getName(), helper.getPropertyType());
						helper.setSetter(setter);

						// add to list
						properties.add(helper);
					} catch (Exception e) {
						System.out.println(e.toString());
						// setter not available, skip property
					}
				}
			}
			properties = properties.stream().sorted().collect(Collectors.toList());
		}

		return properties;
	}

}
