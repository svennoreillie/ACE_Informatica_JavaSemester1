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
	 * Returns if this {@link Item} is leased at the moment.
	 * @param item
	 * @return a boolean specifying if the {@link Item} is leased or not.
	 */
	public boolean isHuidigItemMomenteelUitgeleend(Item item);
	
	/**
	 * 
	 * @param customer
	 * @return a list of all {@link Item}s leased by a specific {@link Customer}.
	 */
	public List<Uitlening> uitleningnenVanKlant (Customer customer);
	
	/**
	 * 
	 * @return a list of all leased {@link Item}s.
	 */
	public List<Uitlening> alleUitleningen ();
	
	/**
	 * 
	 * @return a list of all leased {@link Cd}'d.
	 */
	public List<Uitlening> alleUitleningenVanCd ();
	
	/**
	 * 
	 * @return a list of all leased {@link Dvd}'s.
	 */
	public List<Uitlening> alleUitleningenVanDvd ();
	
	/**
	 * 
	 * @return a list of all leased {@link Game}'s.
	 */
	public List<Uitlening> alleUitleningenVanGame ();
	
		
	/**
	 * Stops a specific {@link Uitlening}.
	 * @param uitlening	the lease that has to be stopped.
	 *
	 */
	public void uitleningVanEenItemStoppen(Uitlening uitlening);
	
	/**
	 * Stops a list of leases.
	 * @param teStoppenItemlijst	the list of {@link Uitlening} that has to be stopped
	 */
	public void uitleningVanMeerdereItemsStoppen (List<Uitlening> teStoppenItemlijst);
	
	/**
	 * 
	 * @param uitlening	the {@link Uitlening} of wich the enddate has to be known.
	 * @return the {@link DateTime} when a specific {@link Uitlening} will end
	 */
	public DateTime geefEindDatumVanDeUitlening (Uitlening uitlening);
	
	/**
	 * 
	 * @return List of all {@link Uitlening}en
	 */
	public List<Uitlening> getAllUitleningen();
}
