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
