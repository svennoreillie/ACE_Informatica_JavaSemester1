package database;

import static org.junit.Assert.*;
import org.junit.Test;

import database.implementations.Data;
import model.*;

public class DataStrategyTest {

	@Test
	public void testGenericClass() throws Throwable {
		Data<Shop> shop = new Data<Shop>(Shop.class);
		DataStrategy<Shop> shopData = new DataStrategy<Shop>(Shop.class);
		assertSame(shop.getClass(), shopData.getService().getClass());
		
		Data<Person> person = new Data<Person>(Person.class);
		DataStrategy<Person> personData = new DataStrategy<Person>(Person.class);
		assertSame(person.getClass(), personData.getService().getClass());
		
		Data<Address> address = new Data<Address>(Address.class);
		DataStrategy<Address> addressData = new DataStrategy<Address>(Address.class);
		assertSame(address.getClass(), addressData.getService().getClass());
	}

}
