package common.factories;

import java.util.Random;

import model.Address;
import model.Customer;
import model.Person;

public class CustomerFactory {

	public static Customer createCustomer(int id){
		final Random rand = new Random();
		Customer customer = null;
		
		// Make a person and address to put into a Customer
		Person person = PersonFactory.getPerson(rand);
		Address address = AddressFactory.getAddress(rand);
		
		// Make an e-mail address using the customer's details
		StringBuffer sbuff = new StringBuffer();
		sbuff.append(person.getFirstName().toLowerCase());
		sbuff.append("." + person.getLastName().toLowerCase());
		sbuff.append("@" + address.getCity().toLowerCase() + ".be");
		String email = new String(sbuff);
		
		// Instantiate the new Customer
		try {
			customer = new Customer(person, address, email);
			customer.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customer;
	}
}
