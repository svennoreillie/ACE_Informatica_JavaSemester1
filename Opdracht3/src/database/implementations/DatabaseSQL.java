package database.implementations;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import common.DBException;
import common.DBMissingException;
import database.internalInterface.DataReadWriteService;
import model.ModelBase;

public class DatabaseSQL<T extends ModelBase> implements DataReadWriteService<T> {

	private final Class<T> classType;

	public DatabaseSQL(Class<T> classType) {
		this.classType = classType;
	}

	
	@Override
	public List<T> readDB() throws DBMissingException, DBException {
		try {
			String dbURL = "jdbc:derby:resources/database;create=true";
			Connection conn = DriverManager.getConnection(dbURL);
			Statement statement = conn.createStatement();
			boolean rs = statement.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, name VARCHAR(30), email VARCHAR(50))");
		
			//verder kijken op : http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm

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

}
