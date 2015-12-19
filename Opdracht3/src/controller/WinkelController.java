package controller;

import java.util.ArrayList;
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
	
	/*public List<Cd> getAllCdSortedByName();
	
	public List<Dvd> getAllDvdSortedByName();
	
	public List<Game> getAllGameSortedByName();*/

	@Override
	public List<Cd> getAllCdSortedByName() {
		List<Item> alleItems = new ArrayList<Item>();
		try {
			Stream<Item> itemStream = dataItem.getAll().stream();
			//itemStream.sorted()
			
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
