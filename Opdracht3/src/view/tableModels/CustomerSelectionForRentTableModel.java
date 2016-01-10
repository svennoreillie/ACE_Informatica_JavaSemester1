package view.tableModels;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.DataStrategy;
import model.Customer;
import model.Item;
import model.Person;

public class CustomerSelectionForRentTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5354974512592663499L;
	private final String[] columnNames = {"Last Name","FirstName","Selected"};
	private final List<Customer> customers;
	private final List<Item> uitTeLenenItems;
	private Customer selectedCustomer;
	private DataService<Customer> customerDB;
	
	public CustomerSelectionForRentTableModel(){
		customers = new LinkedList<Customer>();
		uitTeLenenItems = new LinkedList<Item>();
		selectedCustomer = null;
		customerDB = DataStrategy.getDataService(Customer.class);
		
		try {
			customers.addAll(customerDB.getAll().stream()
				.sorted(new Comparator<Customer>() {
				@Override
				public int compare(Customer c1, Customer c2) {
					int val =c1.getPerson().getLastName().compareTo(c2.getPerson().getLastName());
					if(val==0)
						val = c1.getPerson().getFirstName().compareTo(c2.getPerson().getFirstName());
					return val;
				}}).collect(Collectors.toList()));
		} catch (DBMissingException | DBException e) {
			e.printStackTrace();
		}
		
		fireTableRowsInserted(customers.size()-1, customers.size()-1);
	}
	
	public Customer getSelectedCustomer(){
		return selectedCustomer;
	}
	
	public void searchTable(String searchStr) throws NoSuchElementException, DBMissingException, DBException{
		customers.clear();
		customers.addAll(search(searchStr));
		fireTableRowsInserted(customers.size()-1, customers.size()-1);
	}
	
	private List<Customer> search(String searchStr) throws NoSuchElementException, DBMissingException, DBException{
		return customerDB.getFiltered(cust -> cust.filter(searchStr));
	}
	
	@Override 
	public void setValueAt(Object aValue,int row,int column){
		if(selectedCustomer==customers.get(row)){
			selectedCustomer = null;
		}else if(selectedCustomer==null){
			selectedCustomer=customers.get(row);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Class getColumnClass(int col){
		switch(col){
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return Boolean.class;
		default:
			return null;
		}
	}	
	
	@Override
	public Object getValueAt(int row, int col) {
		Object val;
		Customer customer = customers.get(row);
		
		switch(col){
		case 0:
			val=customer.getPerson().getLastName();
			break;
		case 1:
			val=customer.getPerson().getFirstName();
			break;
		case 2:
			if(selectedCustomer == null)
				val = false;
			else if(customer == selectedCustomer)
				val = true;
			else
				val = false;
			break;
			
			default:
				val=null;
		}
		
		return val;
	}

	@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex==2 && (selectedCustomer==null || selectedCustomer==customers.get(rowIndex));
	}
	
	@Override
	public String getColumnName(int column){
		return columnNames[column];
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}
}
