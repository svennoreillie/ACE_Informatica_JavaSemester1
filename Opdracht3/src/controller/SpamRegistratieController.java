package controller;

import common.DBException;
import common.DBMissingException;
import model.Customer;

public class SpamRegistratieController implements SpamRegistratieService {

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

}
