package view.tableModels;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Item;

public class UitleningTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String[] columnsNames = {"Description" , "Selected"};
	private final LinkedList<Item> items;
	
	public UitleningTableModel() {
		items = new LinkedList<Item>();
	}
	
	public void addItem(Item item){
		items.add(item);
		fireTableRowsInserted(items.size()-1, items.size()-1);
	}
	
	public void addItems(List<Item> items){
		//this.items.addAll(items);
		//fireTableRowsInserted(items.size()-1, items.size()-1);
		for(Item i:items){
			addItem(i);
		}
	}
	
	@Override
	public int getColumnCount() {
		return columnsNames.length;
	}

	@Override
	public int getRowCount() {
		return items.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Object value = null;
		Item item = items.get(row);
		
		switch(column){
		case 0:
			value = item.getTitel();
			break;
		case 1:
			//value = false;
			value = item.getisUitgeleend();
			break;
		
		}
		
		return value;
	}
	
	@Override
	public String getColumnName(int column){
		return columnsNames[column];
	}
	
}
