package model;

import java.util.LinkedList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class CustomerTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1807002911481267147L;
	private static final String[] columnsNames = {"Customer ID", "First Name", "Surname", "E-mail", "Spam"};
	private final LinkedList<Customer> data;
	
	public CustomerTableModel(){
		data = new LinkedList<Customer>();
	}

	public void addCustomer(Customer customer){
		data.add(customer);
		fireTableRowsInserted(data.size()-1, data.size()-1);
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
			value = customer.getId();
			break;
		case 1:
			value = customer.getPerson().getFirstName();
			break;
		case 2:
			value = customer.getPerson().getLastName();
			break;
		case 3:
			value = customer.getEmail();
			break;
		case 4:
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
	
	public Class getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
}
