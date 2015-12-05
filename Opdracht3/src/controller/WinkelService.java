package controller;

import java.util.List;

import common.enums.EnumTypeCd;
import common.enums.EnumTypeDvd;
import common.enums.EnumTypeGame;
import model.Item;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

public interface WinkelService {
		
	public class Winkeldummy {

		public Winkeldummy() {
			// TODO Auto-generated constructor stub
		}

	}

	public void AddItem(Item item);
	
	public void RemoveItem (Item item);
	
	public List<Item> getAllItemsSorted() ;
	
	public List<Cd> searchCd(EnumTypeCd type, String searchString); 
	
	public List<Dvd> searchDvd(EnumTypeDvd type, String searchString); 
	
	public List<Game> searchGames(EnumTypeGame type, String searchString); 
}
