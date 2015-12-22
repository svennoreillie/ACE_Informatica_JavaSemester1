package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

import common.AntiMagicStrings;
import common.DBException;
import common.DBMissingException;
import database.DataStrategy;
import model.Customer;
import model.Item;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;
import java.util.stream.*;

public class WinkelController implements WinkelService {

	DataStrategy<Item> dataItem = new DataStrategy<Item>(Item.class);
	DataStrategy<Cd> dataCd = new DataStrategy<Cd>(Cd.class);
	
	@Override
	public void AddItemToStore(Item item) {
		try {
			dataItem.add(item);
		} catch (DBMissingException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBWriteError);
			e.printStackTrace();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBWriteError);
			e.printStackTrace();
		}
		// TODO Observer: newsletter to all clients who have it activated
	}

	@Override
	public void RemoveItemToStore(Item item) {
		try {
			dataItem.remove(item);
		} catch (DBMissingException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBWriteError);
			e.printStackTrace();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBWriteError);
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Cd> getAllCdSortedByName() {
		try {
			Stream<Cd> itemStream = dataCd.getAll().stream();
			Comparator<Cd> itemComparer = new Comparator<Cd>() {

				@Override
				public int compare(Cd o1, Cd o2) {
					return o1.getTitel().compareTo(o2.getTitel());
				}
				
			};
			itemStream = itemStream.sorted(itemComparer);
			return itemStream.collect(Collectors.toList());
		} catch (DBMissingException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBReadError);
			e.printStackTrace();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBReadError);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Dvd> getAllDvdSortedByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> getAllGameSortedByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cd> searchCdByStringPart(String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dvd> searchDvdByStringPart(String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> searchGameByStringPart(String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> searchItemByStringPart(String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
package controller;

import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import common.AntiMagicStrings;
import common.DBException;
import common.DBMissingException;
import database.*;
import model.Customer;
import model.Item;
import java.util.stream.*;

public class WinkelController<T extends Item> implements WinkelService <T>{

	private DataService<T> dataBase;
	
	public WinkelController(Class<T> entity) {
		
		 dataBase = new DataStrategy<T>(entity);
	}
	
	@Override
	public void AddItemToStore(T entity) {
		try {
			List<T> baseList = dataBase.getAll();
			boolean duplicatedEntity = false;
			dataBase.add(entity);
			for(T entity1 : baseList){
				if (entity1.getTitel() == entity.getTitel()) {
					duplicatedEntity = true;
					break;
				}
			}
			if (!duplicatedEntity) {
				DataStrategy<Customer> dataCustomer = new DataStrategy<Customer>(Customer.class);
				List<Customer> customerList = dataCustomer.getAll();
				for(Customer customerOfList : customerList){
					if (customerOfList.getSpam()) {
						System.out.println("Mail sent to: " + customerOfList.getEmail());
					}	
				}
			}
		} catch (DBMissingException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBWriteError);
			e.printStackTrace();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBWriteError);
			e.printStackTrace();
		}	
	}

	@Override
	public void RemoveItemToStore(T entity) {
		try {
			dataBase.remove(entity);
		} catch (DBMissingException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBWriteError);
			e.printStackTrace();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBWriteError);
			e.printStackTrace();
		}
	}

	@Override
	public List<T> getAllSortedByName() {
		try {
			Stream<T> baseStream = dataBase.getAll().stream();
			Comparator<T> baseComperator = new Comparator<T>(){
				
				@Override
				public int compare(T base1, T base2) {
					return base1.getTitel().compareTo(base2.getTitel());	
				}	
			};
			baseStream = baseStream.sorted(baseComperator);
			return baseStream.collect(Collectors.toList());
			
		} catch (DBMissingException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBReadError);
			e.printStackTrace();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBReadError);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<T> searchByStringPart(String searchString) {
		try {
			List<T> baseList = dataBase.getAll();
			for (T t : baseList) {
				if (!t.getTitel().contains(searchString)) {
					baseList.remove(t);
				}
			}
			return baseList;
			
		} catch (DBMissingException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBReadError);
			e.printStackTrace();
		} catch (DBException e) {
			JOptionPane.showMessageDialog(null, AntiMagicStrings.DBReadError);
			e.printStackTrace();
		}
		return null;
	}
}
