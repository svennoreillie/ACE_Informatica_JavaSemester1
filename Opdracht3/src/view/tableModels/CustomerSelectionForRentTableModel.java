package view.tableModels;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import common.DBException;
import common.DBMissingException;
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
	
	public CustomerSelectionForRentTableModel(){
		customers = new LinkedList<Customer>();
		uitTeLenenItems = new LinkedList<Item>();
		selectedCustomer = null;
		
		try {
			customers.addAll(DataStrategy.getDataService(Customer.class).getAll());
		} catch (DBMissingException | DBException e) {
			e.printStackTrace();
		}
		
		fireTableRowsInserted(customers.size()-1, customers.size()-1);
	}
	
	public Customer getSelectedCustomer(){
		return selectedCustomer;
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
