package database;

/**
 * 
 * @author Sven Noreillie
 *
 */

import static org.junit.Assert.*;
import org.junit.Test;
import database.implementations.Data;
import model.*;

public class DataStrategyTest {

	@Test
	public void testGenericClass() throws Throwable {
		Data<Shop> shop = new Data<Shop>(Shop.class);
		DataService<Shop> shopData = DataStrategy.getDataService(Shop.class);
		assertSame(shop.getClass(), shopData.getClass());
		
		Data<Person> person = new Data<Person>(Person.class);
		DataService<Person> personData = DataStrategy.getDataService(Person.class);
		assertSame(person.getClass(), personData.getClass());
		
		Data<Address> address = new Data<Address>(Address.class);
		DataService<Address> addressData = DataStrategy.getDataService(Address.class);
		assertSame(address.getClass(), addressData.getClass());
	}

}
