package controller;

import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import common.AntiMagicStrings;
import common.DBException;
import common.DBMissingException;
import database.*;
import model.Item;
import java.util.stream.*;

public class WinkelController<T extends Item> implements WinkelService <T>{

	private DataStrategy<T> dataBase;
	
	public WinkelController(Class<T> entity) {
		
		 dataBase = new DataStrategy<T>(entity);
	}
	
	@Override
	public void AddItemToStore(T entity) {
		try {
			dataBase.add(entity);
			// TODO Observer: newsletter to all clients who have it activated
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
			//ToDo .distinct(baseComperator)
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
		// TODO Auto-generated method stub
		return null;
	}


}
