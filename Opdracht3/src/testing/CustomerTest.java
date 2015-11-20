package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.MagicStrings;
import model.Adress;
import model.Customer;
import model.Person;

public class CustomerTest {

	private Customer customer;
	private Person person;
	private Adress adress;
	private int id;
	private String email;
	
	@Before
	public void setUp() throws Exception {
		person = new Person("First", "Last");
		adress = new Adress("Straat", "huisnummer01", "bus01", "postcode1234", "Gemeente", "Belgium");
		id = 1;
		email = "test@test.test";
		
		customer = new Customer(person, adress, id, email);
	}

	@Test
	public void testCustomer() {
		Customer customer2;
		
		person = new Person("First", "Last");
		adress = new Adress("Straat", "huisnummer01", "bus01", "postcode1234", "Gemeente", "Belgium");
		id = 1;
		email = "test@test.test";
		
		try {
			customer2 = new Customer(person, adress, id, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPerson() {
		assertEquals("First Last", this.person.toString());
	}

	@Test
	public void testSetPerson() {
		Person person2 = new Person("Leo", "Haldis");
		
		customer.setPerson(person2);
		assertEquals("Leo Haldis", this.person.toString());
	}

	@Test
	public void testGetAdress() {
		assertEquals("Straat huisnummer01 bus01, postcode1234 Gemeente, Belgium", this.adress.toString());
	}

	@Test
	public void testSetAdress() {
		Adress newAdress = new Adress("abc", "123", "", "1234", "zyx", "Belgium");
		customer.setAdress(newAdress);
		
		assertEquals("abc 123, 1234 zyx, Belgium", this.adress.toString());
	}

	@Test
	public void testGetId() {
		assertEquals(1, customer.getId());
	}

	@Test
	public void testSetId() {
		int testID = 2;
		customer.setId(testID);
		assertEquals(2, customer.getId());
	}

	@Test
	public void testGetEmail() {
		assertEquals("test@test.test", customer.getEmail());
	}

	@Test
	public void testSetEmail() {
		String testEmail = "abc@xyz.com";
		customer.setEmail(testEmail);
		
		assertEquals("abc@xyz.com", customer.getEmail());
	}

}
