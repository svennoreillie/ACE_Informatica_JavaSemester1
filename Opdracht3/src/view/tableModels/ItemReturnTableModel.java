package view.tableModels;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.table.AbstractTableModel;

import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.DataStrategy;
import model.Customer;
import model.Item;
import model.Uitlening;

public class ItemReturnTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7495138998386409899L;
	private static final String[] columnsNames = {"Item ID", "Title", "Return date", "Returned"};
	private final LinkedList<Uitlening> data;
	private DataService<Uitlening> uitleningDB = DataStrategy.getDataService(Uitlening.class);
	private Customer customer;
	
	public ItemReturnTableModel(Customer cust) {
		data = new LinkedList<Uitlening>();
		customer = cust;
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
			value = false;
			break;
		default:
			System.err.println("Error on column index");
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getColumnClass(int c){
		return getValueAt(0, c).getClass();
	}
}
