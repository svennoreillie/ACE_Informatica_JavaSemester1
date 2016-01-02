package database.implementations;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import common.factories.*;
import model.*;

public class TestExcelDatabase {
	private String personString = "model.Person.xls";
	private Path testPersonPath = Paths.get(personString);
	private DatabaseExcel<Person> personDb = new DatabaseExcel<Person>(Person.class);
	
	private String addressString = "model.Address.xls";
	private Path testAddressPath = Paths.get("model.Address.xls");
	private DatabaseExcel<Address> addressDb = new DatabaseExcel<Address>(Address.class);
	
	private String customerString = "model.Customer.xls";
	private Path testCustomerPath = Paths.get("model.Customer.xls");
	private DatabaseExcel<Customer> customerDb = new DatabaseExcel<Customer>(Customer.class);

	@After
	public void tearDown() throws Exception {
		Files.deleteIfExists(testPersonPath);
		Files.deleteIfExists(testAddressPath);
		Files.deleteIfExists(testCustomerPath);
	}

	@Test
	public void testExcelCreate() throws DBMissingException, DBException {
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
		
		File f = new File(personString);
		assertTrue(f.exists());
		assertFalse(f.isDirectory());
		
	}
	
	@Test
	public void testEmptyCustomerDB() throws DBMissingException, DBException {
		List<Customer> customers = customerDb.readDB();
		assertNotNull(customers);
		assertEquals(0, customers.size());
		
		List<Address> addresses = addressDb.readDB();
		assertNotNull(addresses);
		assertEquals(0, addresses.size());
		
		List<Person> persons = personDb.readDB();
		assertNotNull(persons);
		assertEquals(0, persons.size());
	}
	
	@Test
	public void testExcelCreateSubItems() throws DBMissingException, DBException {
		List<Customer> customers = customerDb.readDB();
		
		Customer c = CustomerFactory.createCustomer(1);
		customers.add(c);
		
		customerDb.writeDB(customers);
		
		File f = new File(customerString);
		assertTrue(f.exists());
		assertFalse(f.isDirectory());
		
		 f = new File(addressString);
		assertTrue(f.exists());
		assertFalse(f.isDirectory());
		
		 f = new File(personString);
		assertTrue(f.exists());
		assertFalse(f.isDirectory());
	}
	
	@Test
	public void testExcelCreateSubItemsRead() throws DBMissingException, DBException {
		List<Customer> customers = customerDb.readDB();
		
		Customer c = CustomerFactory.createCustomer(1);
		customers.add(c);
		
		customerDb.writeDB(customers);
		
		customers = customerDb.readDB();
		assertNotNull(customers);
		assertEquals(1, customers.size());
		
		List<Address> addresses = addressDb.readDB();
		assertNotNull(addresses);
		assertEquals(1, addresses.size());
		
		List<Person> persons = personDb.readDB();
		assertNotNull(persons);
		assertEquals(1, persons.size());
		
		assertEquals(c, customers.get(0));
	}

}
