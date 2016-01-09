package database.implementations;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import common.DBException;
import common.DBMissingException;
import database.helpers.DataSource;
import model.Address;
import model.Person;
import model.Shop;

public class TestTextDatabase {

	private Path testModelPath = Paths.get("model.Address.db");
	private DatabaseText<Address> testDB = new DatabaseText<Address>(Address.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataSourceFactory.setType(DataSource.Text);
	}
	
	@Before
	public void setUp() throws Exception {
		Files.createFile(testModelPath);
	}

	@After
	public void tearDown() throws Exception {
		Files.deleteIfExists(testModelPath);
	}
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	

	@Test
	public void testPathCreation() {
		String testString = "model.Address.db";
		assertEquals(testModelPath, testDB.getDbPath());
		assertEquals(testString, testDB.getDbPath().toString());
		
		DatabaseText<Person> personGen = new DatabaseText<Person>(Person.class);
		String personString = "model.Person.db";
		Path visitPath = Paths.get(personString);
		assertEquals(visitPath, personGen.getDbPath());
		assertEquals(personString, personGen.getDbPath().toString());
		
		DatabaseText<Shop> shopGen = new DatabaseText<Shop>(Shop.class);
		String shopString = "model.Shop.db";
		Path addressPath = Paths.get(shopString);
		assertEquals(addressPath, shopGen.getDbPath());
		assertEquals(shopString, shopGen.getDbPath().toString());
	}
	
	@Test(expected=EOFException.class)
	public void testErrorOnEmptyFile() throws DBException, DBMissingException, IOException {
		//remove path created by setup of test
		Files.deleteIfExists(testModelPath);
		testDB.getInputStream();
	}
	
	@Test
	public void testAutoCreateNonExistingPath() throws DBException, DBMissingException, IOException {
		//remove path created by setup of test
		Files.deleteIfExists(testModelPath);
		
		try {
			testDB.getInputStream();
		} catch (EOFException e) {
			//We expect this because file is empty
		}
		assertTrue(Files.exists(testModelPath));
	}
	
	@Test
	public void testWriteRead() throws DBMissingException, DBException {
		DatabaseText<Person> db = new DatabaseText<Person>(Person.class);
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
		
		db.writeDB(persons, false);
		
		List<Person> read = db.readDB();
		assertEquals(3,  read.size());
		Person readP = read.get(0);
		assertEquals(p1, readP);
	}

}
