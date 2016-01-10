package controller;

import java.util.List;
import common.DBException;
import common.DBMissingException;
import common.factories.CustomerFactory;
import database.*;
import model.Customer;

/**
 * 
 * @author Andr� N�brega
 *
 */
public class CustomerController {

	private DataService<Customer> customerData;

	
	public CustomerController() {
		customerData = DataStrategy.getDataService(Customer.class);
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
		Customer originalCustomer = customerData.get(cust.getId());
		cust.getAddress().setId(originalCustomer.getAddress().getId());
		cust.getPerson().setId(originalCustomer.getPerson().getId());
		customerData.update(cust);
	}
}
