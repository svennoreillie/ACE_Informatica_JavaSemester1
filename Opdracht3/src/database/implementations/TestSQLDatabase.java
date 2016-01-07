package database.implementations;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.DBException;
import common.DBMissingException;
import common.factories.CustomerFactory;
import database.helpers.DataSource;
import model.Address;
import model.Customer;
import model.ModelBase;
import model.Person;

public class TestSQLDatabase {

	private DatabaseSQL<Person> personDb = new DatabaseSQL<Person>(Person.class);
	private DatabaseSQL<Customer> customerDb = new DatabaseSQL<Customer>(Customer.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataSourceFactory.setType(DataSource.SQL);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dropTable(personDb);
		personDb.createTable();
		
		dropTable(customerDb);
		customerDb.createTable();
		
	}

	private void dropTable(DatabaseSQL<? extends ModelBase> db) throws SQLException {
		Connection conn = null;
		try {
			conn = personDb.createConnection();
			Statement s = conn.createStatement();
			s.execute("DROP TABLE " + db.getTableName());
			s.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (conn != null) conn.close();
	}

	@Test
	public void testWriteDB() throws DBMissingException, DBException, SQLException {
		List<Person> persons = new ArrayList<Person>();
		Person p1 = new Person();
		p1.setId(1);
		p1.setFirstName("Sven");
		p1.setLastName("Awesome");
		Person p2 = new Person();
		p2.setId(2);
		p2.setFirstName("Peter");
		p2.setLastName("Dude");
		Person p3 = new Person();
		p3.setId(3);
		p3.setFirstName("Andre");
		p3.setLastName("Doc");
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		
		personDb.writeDB(persons);

		List <Person> persoonslijst = personDb.readDB();
		assertTrue(persoonslijst.contains(p1));
		assertTrue(persoonslijst.contains(p2));
		assertTrue(persoonslijst.contains(p3));
		
	}
	
	
	@Test
	public void testComplexWriteDB() throws DBMissingException, DBException, SQLException {
		List<Customer> customers = new ArrayList<Customer>();
		
		for (int i = 0; i < 10; i++) {
			customers.add(CustomerFactory.createCustomer(i));
		}
		
		customerDb.writeDB(customers);
		
		List <Customer> customersReturned = customerDb.readDB();
		assertEquals(10, customersReturned.size());
		
		for (Customer customer : customersReturned) {
			assertTrue(customers.contains(customer));
		}
	}

}