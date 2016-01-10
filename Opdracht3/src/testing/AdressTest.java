package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Address;

/**
 * 
 * @author André Nóbrega
 *
 */
public class AdressTest {
	
	Address test1;
	Address test2;

	@Before
	public void setUp() throws Exception {
		test1 = new Address("abc", "123", "", "1234", "gemeente1", "Belgium");
		test2 = new Address("zyx", "987", "bus 0001", "9876", "gemeente2", "Belgium");
	}

	@Test
	public void testAdress() {
		Address test3 = new Address("Lelielaan", "27", "", "3069", "Buchtbeek", "Belgium");
		assertEquals("Lelielaan 27, 3069 Buchtbeek, Belgium", test3.toString());
	}

	@Test
	public void testSetStreet() {
		test1.setStreet("test");
		assertEquals("test", test1.getStreet());
	}

	@Test
	public void testGetStreet() {
		assertEquals("abc", test1.getStreet());
		assertEquals("zyx", test2.getStreet());
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
		assertEquals("bus 0001", test2.getBox());
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
		assertEquals("1234", test1.getZip());
		assertEquals("9876", test2.getZip());
	}

	@Test
	public void testSetZip() {
		test1.setZip("testzip");
		assertEquals("testzip", test1.getZip());
	}

	@Test
	public void testGetCity() {
		assertEquals("gemeente1", test1.getCity());
		assertEquals("gemeente2", test2.getCity());
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
		assertEquals("abc 123, 1234 gemeente1, Belgium", test1.toString());
		assertEquals("zyx 987 bus 0001, 9876 gemeente2, Belgium", test2.toString());
	}

}
