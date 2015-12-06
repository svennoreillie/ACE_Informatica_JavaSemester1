package controller;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import model.Customer;
import model.Item;
import model.Uitlening;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

public interface UitleenService {
	
	/**
	 * 
	 * @param item
	 * @param customer
	 * @param verhuurPeriodeDagen
	 * @param beginVerhuurDatum
	 * @throws ControllerException
	 */
	public void aanmakenVanEenUitlening(Item item, Customer customer, int verhuurPeriodeDagen, DateTime beginVerhuurDatum) throws ControllerException;
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	public boolean isHuidigItemMomenteelUitgeleend(Item item);
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	public List<Item> uitgeleendeItemsVanHuidigeKlant (Customer customer);
	
	/**
	 * 
	 * @return
	 */
	public List<Item> alleUitgeleendeItems ();
	
	/**
	 * 
	 * @return
	 */
	public List<Cd> alleUitgeleendeCd ();
	
	/**
	 * 
	 * @return
	 */
	public List<Dvd> alleUitgeleendeDvd ();
	
	/**
	 * 
	 * @return
	 */
	public List<Game> alleUitgeleendeGames ();
	
	/**
	 * 
	 * @param uitlening
	 */
	public void uitleningVanEenItemStoppen(Uitlening uitlening);
	
	/**
	 * 
	 * @param teStoppenItemlijst
	 */
	public void uitleningVanMeerdereItemsStoppen (List<Uitlening> teStoppenItemlijst);
	
	/**
	 * 
	 * @param uitlening
	 * @return
	 */
	public DateTime geefEindDatumVanDeUitlening (Uitlening uitlening);
	
}
