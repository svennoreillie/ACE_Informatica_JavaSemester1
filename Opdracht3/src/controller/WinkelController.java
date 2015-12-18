package controller;

import java.util.ArrayList;
import java.util.List;
import java.lang.Iterable;

import common.DBException;
import common.DBMissingException;
import common.enums.EnumTypeCd;
import common.enums.EnumTypeDvd;
import common.enums.EnumTypeGame;
import database.DataStrategy;
import model.Item;
import model.Uitlening;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;
/**
 * Controller for the Shop
 * @author 
 * Peter Vervoort
 * Geert Van Weyenbergh
 *
 */
public class WinkelController implements WinkelService {

	DataStrategy<Item> dataItem = new DataStrategy<Item>(Item.class);
	DataStrategy<Cd> dataCd = new DataStrategy<Cd>(Cd.class);
	
	@Override
	public void AddItem(Item item) {
		try {
			dataItem.add(item);
		} catch (DBMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void RemoveItem(Item item) {
		try {
			dataItem.remove(item);
		} catch (DBMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Item> getAllItemsSorted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cd> searchCd(EnumTypeCd type, String searchString) {
		//eerste oplossing gebruik enkel dataItem (DataStrategy)
		/*List<Item> allItems = new ArrayList<Item>();
		List<Cd> allCds = new ArrayList<Cd>();
		try {
			allItems = dataItem.getAll();
			for(Item item : allItems){
				if(item instanceof Cd){
					allCds.add((Cd) item);
				}
			}
			//haal uit allCds enkel diegene waar searchString ergens in de naam voorkomt.
		} catch (DBMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
		//tweede oplossing gebruik makend van dataCd (DataStrategy)
		List<Cd> alleCds = new ArrayList<Cd>();
		try {
			alleCds = dataCd.getAll();
			//Object list;
			//haal uit allCds enkel diegene waar searchString ergens in de naam voorkomt.
			
			//Geert 17 december
			for (Cd c:alleCds){
				if (((DataStrategy<Cd>) c.dataCd).getAll().contains(searchString)){
				System.out.println("Zoekterm gevonden");
				}
			}
		} catch (DBMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Dvd> searchDvd(EnumTypeDvd type, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> searchGames(EnumTypeGame type, String searchString) {
		// TODO Auto-generated method stub
		return null;
	}

}
