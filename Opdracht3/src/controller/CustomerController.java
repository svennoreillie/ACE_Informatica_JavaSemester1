package controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import common.DBException;
import common.DBMissingException;
import common.factories.CustomerFactory;
import database.DataStrategy;
import model.Customer;

public class CustomerController {

	private DataStrategy<Customer> customerData = new DataStrategy<Customer>(Customer.class);
	
	
	public CustomerController() {

	}
	
	public List<Customer> getList() throws DBMissingException, DBException {
		return customerData.getAll();
	}
	
	public List<Customer> search(String searchString) throws DBMissingException, DBException {
		Stream<Customer> customerStream = customerData.getAll().stream();
		return customerStream.filter(cust -> cust.filter(searchString)).collect(Collectors.toList());
		//is zelfde als hieronder
//		List<Customer> resultList = new ArrayList<Customer>();
//		for (Customer cust : customerData.getAll()) {
//			if (cust.filter(searchString)) resultList.add(cust);
//		}
//		return resultList;
	}
	
	public void addCustomer(Customer customer) throws DBMissingException, DBException{
		customerData.add(customer);
	}
	
	public void createCustomers() throws DBMissingException, DBException{
		for (int i = 0; i < 10; i++){
			customerData.add(CustomerFactory.createCustomer(i));
		}
	}
}
