package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Adress;

public class AdressTest {
	
	Adress test1;
	Adress test2;

	@Before
	public void setUp() throws Exception {
		test1 = new Adress("Lelielaan", "123", "", "9000", "Buchtbeek", "Belgium");
		test2 = new Adress("Brolbaan", "987", "bus 0305", "7000", "Sint-Haldis-Leeuw", "Belgium");
	}

	@Test
	public void testAdress() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStreet() {
		test1.setStreet("test");
		assertEquals("test", test1.getStreet());
	}

	@Test
	public void testGetStreet() {
		System.out.println(test1.getStreet());
		assertEquals("Lelielaan", test1.getStreet());
		
		System.out.println(test2.getStreet());
		assertEquals("Brolbaan", test1.getStreet());
	}

	@Test
	public void testSetNumber() {
		test1.setNumber("69");
		assertEquals("69", test1.getNumber());
	}

	@Test
	public void testGetNumber() {
		assertEquals("123", test1.getNumber());
	}

	@Test
	public void testGetBox() {
		assertEquals("", test1.getBox());
		assertEquals("bus 0305", test2.getBox());
	}

	@Test
	public void testSetBox() {
		test1.setBox("testbus");
		assertEquals("testbus", test1.getBox());
		
		test2.setBox("testbus2");
		assertEquals("testbus2", test2.getBox());
	}

	@Test
	public void testGetZip() {
		assertEquals("9000", test1.getZip());
		assertEquals("7000", test2.getZip());
	}

	@Test
	public void testSetZip() {
		test1.setZip("testzip");
		assertEquals("testzip", test1.getZip());
	}

	@Test
	public void testGetCity() {
		assertEquals("Buchtbeek", test1.getCity());
		assertEquals("Sint-Haldis-Leeuw", test2.getCity());
	}

	@Test
	public void testSetCity() {
		test1.setCity("testcity");
		assertEquals("testcity", test1.getCity());
	}

	@Test
	public void testGetCountry() {
		assertEquals("Belgium", test1.getCountry());
	}

	@Test
	public void testSetCountry() {
		test1.setCountry("France");
		assertEquals("France", test1.getCountry());
	}

	@Test
	public void testToString() {
		assertEquals("Lelielaan 123, 9000 Buchtbeek, Belgium", test1.toString());
		assertEquals("Brolbaan 987 bus 0305, 7000 Sint-Haldis-Leeuw, Belgium", test2.toString());
	}

}
