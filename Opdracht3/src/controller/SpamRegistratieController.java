package controller;

/**
 * 
 * @author Vervoort Peter
 *
 */

import java.util.List;
import common.DBException;
import common.DBMissingException;
import controller.ObserverInterfaces.Observer;
import database.DataService;
import database.DataStrategy;
import model.Customer;

public class SpamRegistratieController implements SpamRegistratieService, Observer {

	CustomerController controller = new CustomerController();
	
	@Override
	public void activerenSpam(Customer customer) throws DBMissingException, DBException {
		customer.setSpam(true);
		controller.updateCustomer(customer);
	}

	@Override
	public void stopSpam(Customer customer) throws DBMissingException, DBException {
		customer.setSpam(false);
		controller.updateCustomer(customer);
	}

	@Override
	public void update(String title) throws DBMissingException, DBException {
		System.out.println("A new unique item titled: " + title + ",");
		System.out.println("sending newsletters!");
		DataService<Customer> dataCustomer = DataStrategy.getDataService(Customer.class);

		List<Customer> customerList = dataCustomer.getAll();
		for(Customer customerOfList : customerList){
			if (customerOfList.getSpam()) {
				System.out.println("Mail sent to: " + customerOfList.getEmail());
			}	
		}
		
	}

}
