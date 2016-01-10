package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import common.AntiMagicStrings;
import common.DBException;
import common.DBMissingException;
import database.DataService;
import database.DataStrategy;
import model.Customer;
import model.Item;
import model.Uitlening;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

/**
 * Keeps a list of all the leases and gives the possibility to create, delete or view leases.
 * @author Huybrechts Pieter
 *
 */
public class UitleenController implements UitleenService {

	
	//List<Uitlening> uitleningenLijst;
	private DataService<Uitlening> uitleningDb;
	
	
	public UitleenController(){
		//uitleningenLijst = new ArrayList<Uitlening>();
		uitleningDb = DataStrategy.getDataService(Uitlening.class);
	}

	@Override
	public void aanmakenVanEenUitlening(Item item, Customer customer, int verhuurPeriodeDagen, DateTime beginVerhuurDatum) throws ControllerException, DBMissingException, DBException {
		//Checks if date is in the past
		if(beginVerhuurDatum.getYear()<DateTime.now().getYear() || 
				beginVerhuurDatum.getMonthOfYear() < DateTime.now().getMonthOfYear() ||
				beginVerhuurDatum.getDayOfMonth() < DateTime.now().getDayOfMonth()){
			throw new ControllerException(AntiMagicStrings.DateInThePast);
		}
		
		//Checks if verhuurPeriodeDagen is positive
		if(verhuurPeriodeDagen<0){
			throw new ControllerException(AntiMagicStrings.NegativeNumberOfDays);
		}
		
		//Checks if item is already rented between beginVerhuurDatum and beginVerhuurDatum+verhuurPeriodeDagen
		for(Uitlening u:getAllUitleningen()){
			if(u.getUitgeleendItem().equals(item)){
				List<DateTime> uItemDates = new ArrayList<DateTime>();
				for(int i = 0 ;i<u.getVerhuurPeriodeInDagen();i++){
					uItemDates.add(u.getBeginVerhuurDatum().plus(i));
				}

				for(int i = 0;i<verhuurPeriodeDagen;i++){
					if(uItemDates.contains(beginVerhuurDatum.plus(i))){
						throw new ControllerException(AntiMagicStrings.ItemAlreadyRentedDuringPeriod);
					}
				}
			}
		}
		
		Uitlening uitlening = new Uitlening();
		uitlening.setUitgeleendItem(item);
		uitlening.setKlantDieUitleent(customer);
		uitlening.setVerhuurPeriodeInDagen(verhuurPeriodeDagen);
		uitlening.setBeginVerhuurDatum(beginVerhuurDatum);
		
		//uitleningenLijst.add(uitlening);
		//item.setisUitgeleend(true);
		try {
			uitleningDb.add(uitlening);
		} catch (DBMissingException | DBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isHuidigItemMomenteelUitgeleend(Item item) {
		try {
			for(Uitlening u:uitleningDb.getAll()){
				if(u.getUitgeleendItem()==item){
					return true;
				}
			}
		} catch (DBMissingException e) {
			e.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public List<Uitlening> uitleningnenVanKlant(Customer customer) throws DBMissingException, DBException {
		List<Uitlening> uitleningen = new ArrayList<Uitlening>();
		
		for(Uitlening u : getAllUitleningen()){
			if(u.getKlantDieUitleent().equals(customer)){
				uitleningen.add(u);
			}
		}
		return uitleningen;
	}

	/*@Override
	public List<Uitlening> alleUitleningen() {
		List<Uitlening> items = new ArrayList<Uitlening>();
		
		for(Uitlening u:uitleningenLijst){
			items.add(u);
		}
		
		return items;
	}*/

	@Override
	public List<Uitlening> alleUitleningenVanCd() throws DBMissingException, DBException {
		return getAllUitleningen().stream().filter(u -> u.getUitgeleendItem().getClass().equals(Cd.class)).collect(Collectors.toList());
	}

	@Override
	public List<Uitlening> alleUitleningenVanDvd() throws DBMissingException, DBException {
		return getAllUitleningen().stream().filter(u -> u.getUitgeleendItem().getClass().equals(Dvd.class)).collect(Collectors.toList());
	}

	@Override
	public List<Uitlening> alleUitleningenVanGame() throws DBMissingException, DBException {
		return getAllUitleningen().stream().filter(u -> u.getUitgeleendItem().getClass().equals(Game.class)).collect(Collectors.toList());
	}

	@Override
	public void uitleningVanEenItemStoppen(Uitlening uitlening) throws DBMissingException, DBException{
		uitleningDb.remove(uitlening);
	}

	@Override
	public void uitleningVanMeerdereItemsStoppen(List<Uitlening> teStoppenItemlijst) throws DBMissingException, DBException {
		for(Uitlening u:teStoppenItemlijst){
			uitleningVanEenItemStoppen(u);
		}
	}

	@Override
	public DateTime geefEindDatumVanDeUitlening(Uitlening uitlening) {
		return uitlening.getBeginVerhuurDatum().plusDays(uitlening.getVerhuurPeriodeInDagen());
	}

	@Override
	public List<Uitlening> getAllUitleningen() throws DBMissingException, DBException {
		return uitleningDb.getAll();
	}

	@Override
	public List<Uitlening> alleUitleningen() throws DBMissingException, DBException {
		return getAllUitleningen();
	}
}
