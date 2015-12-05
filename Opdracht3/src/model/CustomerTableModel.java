package model;

import javax.swing.table.AbstractTableModel;

import org.w3c.dom.views.AbstractView;

public class CustomerTableModel extends AbstractTableModel{
	private String[] columns = {"Customer ID", "First Name", "Surname", "E-mail", "Spam"};

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
