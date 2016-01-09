package view.tableModels;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import model.Item;
import model.Uitlening;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

/**
 * 
 * @author Huybrechts
 *
 */

public class UitleningTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 944470687021694124L;
	private static final String[] columnsNames = {"Description","Type","Id","Rentable","Select"};
	private final LinkedList<Item> items;
	private final Map<Item,Boolean> itemSelectedMap;
	private final LinkedList<Item> itemsToShow;
	private final LinkedList<Item> uitgeleendeItems;
	
	public UitleningTableModel() {
		items = new LinkedList<Item>();
		itemSelectedMap = new HashMap<>();
		itemsToShow = new LinkedList<Item>();
		uitgeleendeItems = new LinkedList<Item>();
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
		
		for(Item item : items){
			itemSelectedMap.put(item, false);
		}
		//fireTableRowsInserted(0,this.items.size()-1);
	}
	
	public void setUitleningen(List<Uitlening> uitleningen){
		for(Uitlening u:uitleningen){
			uitgeleendeItems.add(u.getUitgeleendItem());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void setItemsToShow(Class type){		
		itemsToShow.clear();
		List<Item> itemsToShow;
		if(type==Item.class){
			itemsToShow=items;
		}else{
			itemsToShow = items.stream().filter(item -> item.getClass().equals(type)).collect(Collectors.toList());
		}
		this.itemsToShow.addAll(itemsToShow);
		
		fireTableRowsInserted(itemsToShow.size()-1,itemsToShow.size()-1);
	}
	
	public List<Item> getSelectedItem(){
		List<Item> selectedItems = new ArrayList<>();
		for(Item item:itemSelectedMap.keySet()){
			if(itemSelectedMap.get(item)){
				selectedItems.add(item);
			}
		}
		return selectedItems;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		Object value = null;
		Item item = itemsToShow.get(row);
		
		switch(column){
		case 0:
			value = item.getTitel();
			break;
		case 1:
			String c="";
			
			if(item instanceof Cd){
				c="Cd";
			}if(item instanceof Dvd){
				c="Dvd";
			}if(item instanceof Game){
				c="Game";
			}
			
			value = c;
			break;
		case 2:
			value = item.getId();
			break;
		case 3:
			value = !uitgeleendeItems.contains(itemsToShow.get(row));
			break;
		case 4:
			value=itemSelectedMap.get(item);
			break;
		}
		return value;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int column){
		switch(column){
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return Boolean.class;
		default:
			return null;
		}
	}
	
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	/*if(columnIndex == 4 && !itemsToShow.get(rowIndex).getisUitgeleend()) 
    		return true;
    	else 
    		return false;*/
    	if(columnIndex == 4 && !uitgeleendeItems.contains(itemsToShow.get(rowIndex))) 
    		return true;
    	else 
    		return false;
    }
	
	@Override 
	public void setValueAt(Object aValue,int row,int column){
		itemSelectedMap.replace(itemsToShow.get(row),!itemSelectedMap.get(itemsToShow.get(row)));
	}
	
	@Override
	public String getColumnName(int column){
		return columnsNames[column];
	}
	
	@Override
	public int getColumnCount() {
		return columnsNames.length;
	}

	@Override
	public int getRowCount() {
		return itemsToShow.size();
	}

}
