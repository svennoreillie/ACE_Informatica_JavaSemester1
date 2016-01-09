package database;

/**
 * 
 * @author Sven Noreillie
 *
 */

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMaintenance {
	public void dropAllDBs() {
		try {
			// delete all db files
			File testfile = new File(System.getProperty("user.dir"));

			for (File f : testfile.listFiles()) {
				if (f.getName().endsWith(".db") || f.getName().endsWith(".xls")) {
					f.delete();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// drop sql tables
			Connection conn = null;
			try {
				List<String> tables = new ArrayList<String>();
				String dbURL = "jdbc:derby:resources/database;create=true";
				conn = DriverManager.getConnection(dbURL);
				ResultSet rs = conn.getMetaData().getTables(null, null, "%", null);
				while (rs.next()) {
					String table = rs.getString(3);
					if (table.startsWith("SYS"))
						continue;
					tables.add(table);
				}
				conn.close();

				for (String string : tables) {
					conn = DriverManager.getConnection(dbURL);
					Statement s = conn.createStatement();

					s.execute("DROP TABLE " + string);
					s.close();
					conn.close();
				}

				conn = null;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
