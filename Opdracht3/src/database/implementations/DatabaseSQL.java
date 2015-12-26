package database.implementations;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import common.DBException;
import common.DBMissingException;
import database.helpers.ReflectionPropertyHelper;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class DatabaseSQL<T extends ModelBase> extends ReflectionDatabase<T>implements DataReadWriteService<T> {

	public DatabaseSQL(Class<T> classType) {
		super(classType);
		
		//Create the table in the database
		this.createTable();
	}

	@Override
	public List<T> readDB() throws DBMissingException, DBException {
		try {
			String dbURL = "jdbc:derby:resources/database;create=true";
			Connection conn = DriverManager.getConnection(dbURL);
			Statement statement = conn.createStatement();
			boolean rs = statement
					.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, name VARCHAR(30), email VARCHAR(50))");

			// verder kijken op :
			// http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void writeDB(List<T> list) throws DBMissingException, DBException {
		// TODO Auto-generated method stub

	}

	private Connection createConnection() throws SQLException {
		String dbURL = "jdbc:derby:resources/database;create=true";
		Connection conn = DriverManager.getConnection(dbURL);
		return conn;
	}

	private void createTable() {
		try {
			List<String> columnsList = new ArrayList<String>();
			columnsList.add("Id INTEGER PRIMARY KEY");

			// Get collection of fields to fill column list
			List<ReflectionPropertyHelper> fields = this.getFields();
			for (ReflectionPropertyHelper property : fields) {
				String column = "";
				if (property.getName() == "Id") {
					// continue, automatically added to fields
				} else if (ModelBase.class.isAssignableFrom(property.getPropertyType())) {
					column = property.getName() + " INTEGER";
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
						column += "SMALLINT";
						break;
					case "java.math.BigDecimal":
						column += "DECIMAL";
						break;
					case "java.lang.Double":
						column += "FLOAT";
						break;
					default:
						break;
					}
				}

				columnsList.add(column);
			}

			// Create table query
			String createTableQry = "CREATE TABLE ";
			createTableQry += this.classType.getName();
			createTableQry += " (";
			for (String string : columnsList) {
				createTableQry += string;
			}
			createTableQry += ")";

			// Get a connection & statement
			Connection conn = createConnection();
			try {
				Statement statement = conn.createStatement();
				statement.execute(createTableQry);
			} catch (SQLException e) {
				if (e.getSQLState() == "X0Y32") {
					//table already exists => all is well
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
