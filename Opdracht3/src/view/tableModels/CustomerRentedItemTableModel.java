package view.tableModels;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.DataStrategy;
import model.Customer;
import model.Item;
import model.Uitlening;

public class CustomerRentedItemTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7495138998386409899L;
	private static final String[] columnsNames = {"Item ID", "Title", "Return date"};
	//private final LinkedList<Customer> data;
	private final LinkedList<Uitlening> data;
	private DataService<Uitlening> uitleningDB = DataStrategy.getDataService(Uitlening.class);
	
	public CustomerRentedItemTableModel() {
		data = new LinkedList<Uitlening>();
	}
	
	public void updateTable(String search) throws DBMissingException, DBException{
		data.clear();
		data.addAll(search(search));
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}
	

	private Collection<? extends Uitlening> search(String search) {
		// TODO Andre: filter op Customer
		// Uitlening-lijst wordt éénmaal ingeladen bij het opstarten
		return null;
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
