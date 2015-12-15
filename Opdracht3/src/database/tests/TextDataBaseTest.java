package database.tests;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import common.*;
import model.*;
import database.helpers.*;
import database.implementations.*;
import database.internalInterface.*;


public class TextDataBaseTest {

	private Path testModelPath = Paths.get("model.Address.db");
	private TextDatabase<Address> testDB = new TextDatabase<Address>(Address.class);

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
		
		TextDatabase<Person> personGen = new TextDatabase<Person>(Person.class);
		String personString = "model.Person.db";
		Path visitPath = Paths.get(personString);
		assertEquals(visitPath, personGen.getDbPath());
		assertEquals(personString, personGen.getDbPath().toString());
		
		TextDatabase<Shop> shopGen = new TextDatabase<Shop>(Shop.class);
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
}
