package controller;


import java.util.List;
import org.joda.time.DateTime;

import common.DBException;
import common.DBMissingException;
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
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public void aanmakenVanEenUitlening(Item item, Customer customer, int verhuurPeriodeDagen, DateTime beginVerhuurDatum) throws ControllerException, DBMissingException, DBException;
	
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
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public List<Uitlening> uitleningnenVanKlant (Customer customer) throws DBMissingException, DBException;
	
	/**
	 * 
	 * @return a list of all leased {@link Item}s.
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public List<Uitlening> alleUitleningen () throws DBMissingException, DBException;
	
	/**
	 * 
	 * @return a list of all leased {@link Cd}'d.
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public List<Uitlening> alleUitleningenVanCd () throws DBMissingException, DBException;
	
	/**
	 * 
	 * @return a list of all leased {@link Dvd}'s.
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public List<Uitlening> alleUitleningenVanDvd () throws DBMissingException, DBException;
	
	/**
	 * 
	 * @return a list of all leased {@link Game}'s.
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public List<Uitlening> alleUitleningenVanGame () throws DBMissingException, DBException;
	
		
	/**
	 * Stops a specific {@link Uitlening}.
	 * @param uitlening	the lease that has to be stopped.
	 * @throws DBException 
	 * @throws DBMissingException 
	 *
	 */
	public void uitleningVanEenItemStoppen(Uitlening uitlening) throws DBMissingException, DBException;
	
	/**
	 * Stops a list of leases.
	 * @param teStoppenItemlijst	the list of {@link Uitlening} that has to be stopped
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public void uitleningVanMeerdereItemsStoppen (List<Uitlening> teStoppenItemlijst) throws DBMissingException, DBException;
	
	/**
	 * 
	 * @param uitlening	the {@link Uitlening} of wich the enddate has to be known.
	 * @return the {@link DateTime} when a specific {@link Uitlening} will end
	 */
	public DateTime geefEindDatumVanDeUitlening (Uitlening uitlening);
	
	/**
	 * 
	 * @return List of all {@link Uitlening}en
	 * @throws DBException 
	 * @throws DBMissingException 
	 */
	public List<Uitlening> getAllUitleningen() throws DBMissingException, DBException;
}
