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
import database.DataSourceFactory;
import database.helpers.*;
import database.implementations.*;
import database.internalInterface.*;


public class TextDataBaseTest {

	private Path testModelPath = Paths.get("model.Address.db");
	private TextDatabase<Address> testDB = new TextDatabase<Address>();

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
		String testString = "testing.TestModel.db";
		assertEquals(testModelPath, testDB.getDbPath());
		assertEquals(testString, testDB.getDbPath().toString());
		
		TextDatabase<Person> personGen = new TextDatabase<Person>();
		String visitString = "model.Visit.db";
		Path visitPath = Paths.get(visitString);
		assertEquals(visitPath, personGen.getDbPath());
		assertEquals(visitString, personGen.getDbPath().toString());
		
		TextDatabase<Shop> shopGen = new TextDatabase<Shop>();
		String addressString = "model.Address.db";
		Path addressPath = Paths.get(addressString);
		assertEquals(addressPath, shopGen.getDbPath());
		assertEquals(addressString, shopGen.getDbPath().toString());
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
