package database.implementations;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.DBException;
import common.DBMissingException;
import model.Person;

public class TestSQLDatabase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadDB() throws DBMissingException, DBException {
		DatabaseSQL<Person> sql = new DatabaseSQL<Person>(Person.class);
		sql.readDB();
	}

	@Test
	public void testWriteDB() {
		fail("Not yet implemented"); // TODO
	}

}
