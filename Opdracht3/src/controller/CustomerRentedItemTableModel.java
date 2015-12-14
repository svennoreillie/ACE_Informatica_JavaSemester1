package controller;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import model.Item;

public class CustomerRentedItemTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7367152754929073077L;
	private static final String[] columnNames = {"Type", "Title", "Return date"};
	private final LinkedList<Item> data;

	public CustomerRentedItemTableModel(){
		data = new LinkedList<Item>();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	
	@Override
	public String getColumnName(int column){
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object value = null;
		Item item = data.get(row);
		
		switch(col){
		case 0: //Type item (CD, DVD, Game)
			value = item.getClass();
			break;
		case 1: //Titel
			value = item.getTitel();
			break;
		case 2://Terugbezorgdatum
			value = "DELIVERY DATE GOES HERE";
			//value = item.get
			break;
		default:
			System.err.println("Error on column index!");
		}
		
		if (value == null){
			value = "ERROR!";
			System.err.println("Error on filling out cell");
		}
		
		return null;
	}

}
