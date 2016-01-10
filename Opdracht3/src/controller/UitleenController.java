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

	
	List<Uitlening> uitleningenLijst;
	private DataService<Uitlening> uitleningData = DataStrategy.getDataService(Uitlening.class);
	
	
	public UitleenController(){
		uitleningenLijst = new ArrayList<Uitlening>();
	}

	@Override
	public void aanmakenVanEenUitlening(Item item, Customer customer, int verhuurPeriodeDagen, DateTime beginVerhuurDatum) throws ControllerException {
		
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
		for(Uitlening u:uitleningenLijst){
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
		
		uitleningenLijst.add(uitlening);
		//item.setisUitgeleend(true);
		try {
			DataStrategy.getDataService(Uitlening.class).add(uitlening);
		} catch (DBMissingException | DBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isHuidigItemMomenteelUitgeleend(Item item) {
		try {
			for(Uitlening u:uitleningData.getAll()){
				if(u.getUitgeleendItem()==item){
					return true;
				}
			}
		} catch (DBMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public List<Uitlening> uitleningnenVanKlant(Customer customer) {
		List<Uitlening> items = new ArrayList<Uitlening>();
		
		for(Uitlening u : uitleningenLijst){
			if(u.getKlantDieUitleent().equals(customer)){
				items.add(u);
			}
		}
		
		return items;
	}

	@Override
	public List<Uitlening> alleUitleningen() {
		List<Uitlening> items = new ArrayList<Uitlening>();
		
		for(Uitlening u:uitleningenLijst){
			items.add(u);
		}
		
		return items;
	}

	@Override
	public List<Uitlening> alleUitleningenVanCd() {
		return uitleningenLijst.stream().filter(u -> u.getUitgeleendItem().getClass().equals(Cd.class)).collect(Collectors.toList());
	}

	@Override
	public List<Uitlening> alleUitleningenVanDvd() {
		return uitleningenLijst.stream().filter(u -> u.getUitgeleendItem().getClass().equals(Dvd.class)).collect(Collectors.toList());
	}

	@Override
	public List<Uitlening> alleUitleningenVanGame() {
		return uitleningenLijst.stream().filter(u -> u.getUitgeleendItem().getClass().equals(Game.class)).collect(Collectors.toList());
	}

	@Override
	public void uitleningVanEenItemStoppen(Uitlening uitlening) {
		//uitlening.getUitgeleendItem().setisUitgeleend(false);
		uitleningenLijst.remove(uitlening);
		
		try {
			DataStrategy.getDataService(Uitlening.class).remove(uitlening);
		} catch (DBMissingException e) {
			// Magicstring goes here
			e.printStackTrace();
		} catch (DBException e) {
			// Magicstring goes here
			e.printStackTrace();
		}
	}

	@Override
	public void uitleningVanMeerdereItemsStoppen(List<Uitlening> teStoppenItemlijst) {
		for(Uitlening u:teStoppenItemlijst){
			uitleningVanEenItemStoppen(u);
		}
	}

	@Override
	public DateTime geefEindDatumVanDeUitlening(Uitlening uitlening) {
		return uitlening.getBeginVerhuurDatum().plusDays(uitlening.getVerhuurPeriodeInDagen());
	}

	@Override
	public List<Uitlening> getAllUitleningen() {
		return uitleningenLijst;
	}
	
	public List<Uitlening> getList() throws DBMissingException, DBException {
		return uitleningData.getAll();
	}

}
