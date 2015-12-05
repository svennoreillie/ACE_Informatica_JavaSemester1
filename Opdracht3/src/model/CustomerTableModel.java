package model;

import javax.swing.table.AbstractTableModel;

public class CustomerTableModel extends AbstractTableModel{
	private String[] columnNames = {"CustomerID", "FirstName", "LastName", "Email"};
	private ModelBase[][] data ;
	
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
}
