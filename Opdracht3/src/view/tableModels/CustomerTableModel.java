package view.tableModels;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.table.AbstractTableModel;

import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.DataStrategy;
import model.Customer;


public class CustomerTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private DataService<Customer> customerDB = DataStrategy.getDataService(Customer.class);
	
	private static final long serialVersionUID = 1807002911481267147L;
	private static final String[] columnsNames = {"First Name", "Surname", "E-mail", "Newsletter"};
	private final LinkedList<Customer> data;
	
	public CustomerTableModel(){
		data = new LinkedList<Customer>();
	}
	
	public void updateTable() throws DBMissingException, DBException{
		data.clear();
		data.addAll(customerDB.getAll());
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}
	
	public void updateTable(String searchString) throws DBMissingException, DBException{
		data.clear();
		data.addAll(search(searchString));
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}
	
	private List<Customer> search(String searchString) throws DBMissingException, DBException {
		return customerDB.getFiltered(cust -> cust.filter(searchString));
	}

	@Override
	public int getColumnCount() {
		return columnsNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Object value = null;
		Customer customer = data.get(row);
		
		switch(column){
		case 0:
			value = customer.getPerson().getFirstName();
			break;
		case 1:
			value = customer.getPerson().getLastName();
			break;
		case 2:
			value = customer.getEmail();
			break;
		case 3:
			value = customer.getSpam();
			break;
		default:
			System.err.println("Error on column index!");
		}
		
		if (value == null){
			value = "ERROR!";
		}
		
		return value;
	}
	
	@Override
	public String getColumnName(int column){
		return columnsNames[column];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}	
}