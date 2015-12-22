package database.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.DBException;
import common.DBMissingException;
import model.Person;

public class TestExcelDatabase {

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
	public void test() throws DBMissingException, DBException {
		DatabaseExcel<Person> db = new DatabaseExcel<Person>(Person.class);
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
		
		db.writeDB(persons);
	}

}
