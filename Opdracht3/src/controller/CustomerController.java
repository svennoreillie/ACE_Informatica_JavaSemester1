package controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import common.DBException;
import common.DBMissingException;
import common.factories.CustomerFactory;
import database.*;
import model.Customer;

/**
 * 
 * @author André Nóbrega
 *
 */
public class CustomerController {

	private DataService<Customer> customerData = DataStrategy.getDataService(Customer.class);

	
	public CustomerController() {

	}
	
	public List<Customer> getList() throws DBMissingException, DBException {
		return customerData.getAll();
	}
	
	public void addCustomer(Customer customer) throws DBMissingException, DBException{
		customerData.add(customer);
	}
	
	public void createCustomers() throws DBMissingException, DBException{
		for (int i = 0; i < 10; i++){
			customerData.add(CustomerFactory.getCustomer());
		}
	}
	
	public void updateCustomer(Customer cust) throws DBMissingException, DBException{
		customerData.update(cust);
	}
}
