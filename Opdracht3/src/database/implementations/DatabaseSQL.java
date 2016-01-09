package database.implementations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.helpers.ReflectionPropertyHelper;
import database.internalInterface.DataReadWriteService;
import model.Item;
import model.ModelBase;

public class DatabaseSQL<T extends ModelBase> extends ReflectionDatabase<T>implements DataReadWriteService<T> {

	public DatabaseSQL(Class<T> classType) {
		super(classType);

		if (this.classType == Item.class) {
			System.err.println();
		}
		
		// Create the table in the database
		this.createTable();
	}

	@Override
	public List<T> readDB() throws DBMissingException, DBException {
		try {
			Connection conn = this.createConnection();
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM " + getTableName());

			// load structure of T
			List<ReflectionPropertyHelper> genericFieldArray = getFields();
			List<T> returnList = new ArrayList<T>();

			while (results.next()) {
				T instance = this.classType.newInstance();
				for (ReflectionPropertyHelper property : genericFieldArray) {
					Object value = null;
					Method set = property.getSetter();
					String colName = property.getName();
					switch (property.getPropertyType().getName()) {
					case "java.lang.String":
						value = results.getString(colName);
						break;
					case "int":
						value = results.getInt(colName);
						break;
					case "boolean":
						value = results.getBoolean(colName);
						break;
					case "java.lang.Boolean":
						value = results.getBoolean(colName);
						break;
					case "java.lang.Integer":
						value = results.getInt(colName);
						break;
					case "java.math.BigDecimal":
						value = results.getBigDecimal(colName);
						break;
					case "java.lang.Double":
						value = results.getDouble(colName);
						break;
					case "org.joda.time.DateTime":
						set.invoke(instance, org.joda.time.DateTime.parse(results.getString(colName)));
						break;
					default:
						// ModelBase case
						if (ModelBase.class.isAssignableFrom(property.getPropertyType())) {
							if (Modifier.isAbstract(property.getPropertyType().getModifiers())) {
								String[] splittedContent = results.getString(colName).split(" ");
								String classType = splittedContent[0];
								int id = Integer.parseInt(splittedContent[1]);

								DataService<? extends ModelBase> strategy = GetDedicatedDataService(classType);
								ModelBase reference = strategy.get(id);
								set.invoke(instance, property.getPropertyType().cast(reference));

							} else {
								// get from dedicated DataService
								DataService<? extends ModelBase> strategy = GetDedicatedDataService(
										property.getPropertyType().getName());
								int id = results.getInt(colName);
								ModelBase reference = strategy.get(id);
								set.invoke(instance, property.getPropertyType().cast(reference));
								value = null;
							}
						}
						break;
					}
					if (value != null)
						set.invoke(instance, value);
				}
				returnList.add(instance);
			}

			results.close();
			statement.close();
			conn.close();

			return returnList;
		} catch (SQLException e) {
			throw new DBException("Error reading from SQL database " + getTableName(), e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO :: exceptions gooien
		return null;
	}

	@Override
	public void writeDB(List<T> list, Boolean update) throws DBMissingException, DBException {
		String insertQuery = "";
		try {
			// clear entire table
			// TODO:: improve this, worst idea everv
			try {
				Connection conn = this.createConnection();
				Statement clearStatement = conn.createStatement();
				clearStatement.execute("DELETE FROM " + getTableName());
				clearStatement.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Failure clearing db before write => " + e.toString());
			}

			// load structure of T
			List<ReflectionPropertyHelper> genericFieldArray = getFields();

			insertQuery = "INSERT INTO " + getTableName() + " (";
			int colIndex = 0;
			for (ReflectionPropertyHelper property : genericFieldArray) {
				if (colIndex != 0)
					insertQuery += ", ";
				insertQuery += property.getName();
				colIndex++;
			}
			insertQuery += ") VALUES ";
			for (T item : list) {
				String rowQuery = "";
				for (ReflectionPropertyHelper property : genericFieldArray) {
					Object value;
					try {
						if (ModelBase.class.isAssignableFrom(property.getPropertyType())) {
							// found property is a ModelBase => lookup id to
							// write in this table
							// leave storing of the found ModelBase to an
							// instance of DatabaseSQL with type of found
							// ModelBase
							
							// get the value of this property for the current
							ModelBase model = (ModelBase) property.getGetter().invoke(item);
							
							//Get classtype of instance, if it exists
							if (model == null) continue;
							String classTypeString = model.getClass().getName();
							// ugly helper method to create a DataService, could
							// not find a way to do it generic
							DataService<? extends ModelBase> strategy = GetDedicatedDataService(classTypeString);

							
							// item in the list & save to other DataService
							if (update) {
								strategy.update(model);
							} else {
								strategy.add(model);
							}
							
							
							// save id of this item in our own excel
							value = model.getId();

							if (Modifier.isAbstract(property.getPropertyType().getModifiers())) {
								// Could be the abstract item class => prefix
								// with classtype
								value = model.getClass().getName() + " " + value;
							}
						} else {
							// normal property, get the object for this item in
							// the list
							value = property.getGetter().invoke(item);
						}

						if (value != null) {
							if (rowQuery.length() > 0)
								rowQuery += ", ";

							switch (value.getClass().getName()) {
							case "java.lang.String":
								rowQuery += ("'" + value.toString() + "'");
								break;
							case "boolean":
								if ((boolean) value) {
									rowQuery += "TRUE";
								} else {
									rowQuery += "FALSE";
								}
								break;
							case "java.lang.Boolean":
								if ((boolean) value) {
									rowQuery += "TRUE";
								} else {
									rowQuery += "FALSE";
								}
								break;
							case "int":
								rowQuery += value.toString();
								break;
							case "java.lang.Integer":
								rowQuery += value.toString();
								break;
							case "java.math.BigDecimal":
								rowQuery += value.toString();
								break;
							case "java.lang.Double":
								rowQuery += value.toString();
								break;
							case "org.joda.time.DateTime":
								rowQuery += ("'" + value.toString() + "'");
								break;
							default:
								rowQuery += ("'" + value.toString() + "'");
								break;
							}
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO :: logging
					} catch (InvocationTargetException e) {
						// error invoking the getter => just continue
						// TODO:: logging
					}
				}

				insertQuery += "(" + rowQuery + "),";
			}

			insertQuery = insertQuery.substring(0, insertQuery.length() - 1);

			Connection conn = this.createConnection();
			Statement clearStatement = conn.createStatement();
			clearStatement.execute(insertQuery);
			clearStatement.close();
			conn.close();
		} catch (Exception e) {
			throw new DBException("Error writing to SQL database " + getTableName() + "---" + insertQuery, e);
		}
	}

	protected String getTableName() {
		String className = this.classType.getName();
		int index = className.lastIndexOf('.');
		className = className.substring(index + 1);
		return className;
	}

	protected Connection createConnection() throws SQLException {
		String dbURL = "jdbc:derby:resources/database;create=true";
		Connection conn = DriverManager.getConnection(dbURL);
		return conn;
	}

	protected void createTable() {
		try {
			List<String> columnsList = new ArrayList<String>();
			columnsList.add("Id INTEGER PRIMARY KEY");

			// Get collection of fields to fill column list
			List<ReflectionPropertyHelper> fields = this.getFields();
			for (ReflectionPropertyHelper property : fields) {
				String column = "";
				if (property.getName().equalsIgnoreCase("Id")) {
					// continue, automatically added to fields
					continue;
				} else if (ModelBase.class.isAssignableFrom(property.getPropertyType())) {
					if (Modifier.isAbstract(property.getPropertyType().getModifiers())) {
						// Could be the abstract item class => prefix
						// with classtype
						column = property.getName() + " VARCHAR(250)";
					} else {
						column = property.getName() + " INTEGER";
					}
				} else {
					// normal property
					column = property.getName() + " ";
					switch (property.getPropertyType().getName()) {
					case "java.lang.String":
						column += "VARCHAR(250)";
						break;
					case "int":
						column += "INTEGER";
						break;
					case "boolean":
						column += "BOOLEAN";
						break;
					case "java.math.BigDecimal":
						column += "DECIMAL";
						break;
					case "java.lang.Double":
						column += "FLOAT";
						break;
					default:
						column += "VARCHAR(250)";
						break;
					}
				}

				columnsList.add(column);
			}

			// Create table query
			String createTableQry = "CREATE TABLE ";
			createTableQry += getTableName();
			createTableQry += " (";
			int i = 0;
			for (String string : columnsList) {
				if (i > 0)
					createTableQry += ",";
				createTableQry += string;
				i++;
			}
			createTableQry += ")";

			// Get a connection & statement
			Connection conn = createConnection();
			try {
				Statement statement = conn.createStatement();
				statement.execute(createTableQry);
				statement.close();
			} catch (SQLException e) {
				if (e.getSQLState().equalsIgnoreCase("X0Y32")) {
					// table already exists => all is well
				} else {
					throw e;
				}
			}
			conn.close();

			// verder kijken op :
			// http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

}