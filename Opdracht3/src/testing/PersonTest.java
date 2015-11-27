package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Person;

public class PersonTest {
	
	private Person test;

	@Before
	public void setUp() throws Exception {
		test = new Person("Voornaam", "Familienaam");
	}

	@Test
	public void testPerson() {
		Person test2 = new Person("Leo", "Haldis");
		assertEquals("Leo Haldis", test2.toString());
	}

	@Test
	public void testGetFirstName() {
		assertEquals("Voornaam", test.getFirstName());
	}

	@Test
	public void testSetFirstName() {
		test.setFirstName("first");
		assertEquals("first", test.getFirstName());
	}

	@Test
	public void testGetLastName() {
		assertEquals("Familienaam", test.getLastName());
	}

	@Test
	public void testSetLastName() {
		test.setLastName("last");
		assertEquals("last", test.getLastName());
	}

	@Test
	public void testToString() {
		assertEquals("Voornaam Familienaam", test.toString());
	}

}
