package controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import common.DBException;
import common.DBMissingException;
import common.factories.CustomerFactory;
import database.*;
import model.Customer;

public class CustomerController {

	private DataService<Customer> customerData = DataStrategy.getDataService(Customer.class);

	
	public CustomerController() {

	}
	
	public List<Customer> getList() throws DBMissingException, DBException {
		return customerData.getAll();
	}
	
	public List<Customer> search(String searchString) throws DBMissingException, DBException {
		Stream<Customer> customerStream = customerData.getAll().stream();
		return customerStream.filter(cust -> cust.filter(searchString)).collect(Collectors.toList());
	}
	
	public void addCustomer(Customer customer) throws DBMissingException, DBException{
//		customer.setId(customerData.getAll().size()+1);
		customerData.add(customer);
	}
	
	public void createCustomers() throws DBMissingException, DBException{
		for (int i = 0; i < 10; i++){
			int id = customerData.getAll().size() + 1;
			customerData.add(CustomerFactory.createCustomer(id));
//			customerData.add(CustomerFactory.createCustomer());
		}
	}
	
	public void updateCustomer(Customer cust) throws DBMissingException, DBException{
		customerData.update(cust);
	}
}
