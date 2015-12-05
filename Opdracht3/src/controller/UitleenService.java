package controller;

import java.util.Date;
import java.util.List;

import model.Customer;
import model.Item;
import model.Uitlening;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

public interface UitleenService {

	public void aanmakenVanEenUitlening(Item item, Customer customer, int verhuurPeriodeDagen, Date beginVerhuurDatum);
	
	public boolean isHuidigItemMomenteelUitgeleend(Item item);
	
	public List<Item> uitgeleendeItemsVanHuidigeKlant (Customer customer);
	
	public List<Item> alleUitgeleendeItems ();
	
	public List<Cd> alleUitgeleendeCd ();
	
	public List<Dvd> alleUitgeleendeDvd ();
	
	public List<Game> alleUitgeleendeGames ();
	
	public void uitleningVanEenItemStoppen(Uitlening uitlening);
	
	public void uitleningVanMeerdereItemsStoppen (List<Uitlening> teStoppenItemlijst);
	
	public Date geefEindDatumVanDeUitlening (Uitlening uitlening);
	
}
