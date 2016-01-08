package view.tableModels;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import model.Item;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

public class UitleningTableModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String[] columnsNames = {"Description" , "Select"};
	private final LinkedList<Item> items;
	
	public UitleningTableModel() {
		items = new LinkedList<Item>();
	}
	
	public void addItem(Item item){
		items.add(item);
		fireTableRowsInserted(items.size()-1, items.size()-1);
	}
	
	public void setItems(List<Item> items){
		
		List<Item> allItemsSorted = items.stream().sorted(new Comparator<Item>(){

			@Override
			public int compare(Item item1, Item item2) {
				int result;
				result = item1.getTitel().compareTo(item2.getTitel());
				
				if(result==0){
					if(item1 instanceof  Cd){
						if(item2 instanceof  Cd){
							result = 0;
						}
						
						if(item2 instanceof Dvd){
							result = -1;
						}
						
						if(item2 instanceof  Game){
							result = -1;
						}
					}
					
					if(item1 instanceof Dvd){
						if(item2 instanceof  Cd){
							result = 1;
						}
						
						if(item2 instanceof Dvd){
							result = 0;
						}
						
						if(item2 instanceof  Game){
							result = -1;
						}
					}
					
					if(item1 instanceof Game){
						if(item2 instanceof  Cd){
							result = 1;
						}
						
						if(item2 instanceof Dvd){
							result = 1;
						}
						
						if(item2 instanceof  Game){
							result = 0;
						}
					}
				}
				
				return result;
			}
			
		}).collect(Collectors.toList());
		
		
		this.items.clear();
		this.items.addAll(allItemsSorted);
		fireTableRowsInserted(0,this.items.size()-1);
		
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
	
	@Override
	public Class getColumnClass(int column){
		switch(column){
		case 0:
			return String.class;
		
		default:
			return Boolean.class;
		}
	}
	
	
}
