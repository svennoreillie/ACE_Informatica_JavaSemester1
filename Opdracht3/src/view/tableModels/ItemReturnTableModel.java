package view.tableModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.swing.table.AbstractTableModel;
import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.DataStrategy;
import model.Customer;
import model.Uitlening;

/**
 * 
 * @author André Nóbrega
 *
 */
public class ItemReturnTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7495138998386409899L;
	private static final String[] columnsNames = {"Item ID", "Title", "Return date", "Returning"};
	private final LinkedList<Uitlening> data;
	private DataService<Uitlening> uitleningDB = DataStrategy.getDataService(Uitlening.class);
	private final Map<Uitlening,Boolean> itemSelectedMap;
	private Customer customer;
	
	public ItemReturnTableModel(Customer cust) {
		data = new LinkedList<Uitlening>();
		customer = cust;
		itemSelectedMap = new HashMap<>();
	}
	
	public void updateTable(String search) throws DBMissingException, DBException{
		data.clear();
		data.addAll(search(search));
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}
	
	private List<Uitlening> search(String searchString) throws DBMissingException, DBException {
		return uitleningDB.getFiltered(uitl -> uitl.filter(searchString));
	}
	
	public void updateTable() throws NoSuchElementException, DBMissingException, DBException{
		data.clear();
		data.addAll(uitleningDB.getFiltered(uitl -> uitl.filter(String.valueOf(customer.getId()))));
		fireTableRowsInserted(data.size()-1, data.size()-1);

		for(Uitlening uitlening : data){
		itemSelectedMap.put(uitlening, false);
		}
	}
	
	public List<Uitlening> getSelectedItems(){
		List<Uitlening> selectedItems = new ArrayList<>();
		for(Uitlening uitlening:itemSelectedMap.keySet()){
			if(itemSelectedMap.get(uitlening)){
				selectedItems.add(uitlening);
			}
		}
		return selectedItems;	
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
		Uitlening uitlening = data.get(row);
		
		switch(column){
		case 0:
			value = uitlening.getUitgeleendItem().getId();
			break;
		case 1:
			value = uitlening.getUitgeleendItem().getTitel();
			break;
		case 2:
			value = uitlening.getBeginVerhuurDatum().plusDays(uitlening.getVerhuurPeriodeInDagen());
			break;
		case 3:
			value = itemSelectedMap.get(uitlening);
			break;
		default:
			System.err.println("Error on column index");
			break;
		}
		
		if (value == null){
			value = "ERROR!";
		}
		
		return value;
	}
	
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
			return Boolean.class;
		default:
			return null;
		}
	}
	
	 @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	    	if(columnIndex == 3) 
	    		return true;
	    	else 
	    		return false;
	    }
	
	@Override 
	public void setValueAt(Object aValue,int row,int column){
		//
		itemSelectedMap.replace(data.get(row),!itemSelectedMap.get(data.get(row)));
	}
	
	@Override
	public String getColumnName(int column){
		return columnsNames[column];
	}
}
