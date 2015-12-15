package controller;

import java.util.List;
import java.util.stream.Stream;

import common.DBException;
import common.DBMissingException;
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
		return (List<Customer>) customerStream.filter(cust -> cust.filter(searchString));
		//is zelfde als hieronder
//		List<Customer> resultList = new ArrayList<Customer>();
//		for (Customer cust : customerData.getAll()) {
//			if (cust.filter(searchString)) resultList.add(cust);
//		}
//		return resultList;
	}
	
	
	
	public List<Customer> search(Customer searchCustomer) throws DBMissingException, DBException {
		// TODO ANDRE => maak bovenstaande method nu ook eens voor een gedetailleerdtdt object
		return null;
	}
	
}
