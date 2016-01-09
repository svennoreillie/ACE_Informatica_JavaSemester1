package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.Customer;
import model.Person;

/**
 * 
 * @author André Nóbrega
 *
 */
public class CustomerTest {

	private Customer customer;
	private Person person;
	private Address adress;
	private int id;
	private String email;
	
	@Before
	public void setUp() throws Exception {
		person = new Person("First", "Last");
		adress = new Address("Straat", "huisnummer01", "bus01", "postcode1234", "Gemeente", "Belgium");
		email = "test@test.test";
		
		customer = new Customer(person, adress, email);
	}

	@Test
	public void testCustomer() {
		Customer customer2;
		
		person = new Person("First", "Last");
		adress = new Address("Straat", "huisnummer01", "bus01", "postcode1234", "Gemeente", "Belgium");
		email = "test@test.test";
		
		try {
			customer2 = new Customer(person, adress, email);
		} catch (Exception e) {
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
		assertEquals("Leo Haldis", customer.getPerson().toString());
	}

	@Test
	public void testGetAdress() {
		assertEquals("Straat huisnummer01 bus01, postcode1234 Gemeente, Belgium", this.adress.toString());
	}

	@Test
	public void testSetAdress() {
		Address newAdress = new Address("abc", "123", "", "1234", "zyx", "Belgium");
		customer.setAddress(newAdress);
		assertEquals("abc 123, 1234 zyx, Belgium", customer.getAddress().toString());
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
