package view.tableModels;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import model.Customer;

public class CustomerRentedItemTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7495138998386409899L;
	private static final String[] columnsNames = {"First Name", "Surname", "E-mail", "Spam"};
	private final LinkedList<Customer> data;
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
