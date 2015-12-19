package controller;

import java.util.List;
import model.Item;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

public interface WinkelService {

	public void AddItemToStore(Item item);
	
	public void RemoveItemToStore (Item item);
	
	public List<Cd> getAllCdSortedByName();
	
	public List<Dvd> getAllDvdSortedByName();
	
	public List<Game> getAllGameSortedByName();
	
	public List<Cd> searchCdByStringPart(String searchString); 
	
	public List<Dvd> searchDvdByStringPart(String searchString); 
	
	public List<Game> searchGameByStringPart(String searchString);
	
	public List<Item> searchItemByStringPart(String searchString);
	
}
