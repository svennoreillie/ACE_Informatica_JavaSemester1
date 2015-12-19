package controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import common.AntiMagicStrings;
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

	
	List<Uitlening> uitleningen;
	
	
	public UitleenController(){
		uitleningen = new ArrayList<Uitlening>();
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
		for(Uitlening u:uitleningen){
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
		
		uitleningen.add(uitlening);
		item.setUitgeleend(true);
	}

	@Override
	public boolean isHuidigItemMomenteelUitgeleend(Item item) {
		return item.isUitgeleend();
	}

	@Override
	public List<Uitlening> uitleningnenVanKlant(Customer customer) {
		List<Uitlening> items = new ArrayList<Uitlening>();
		
		for(Uitlening u : uitleningen){
			if(u.getKlantDieUitleent().equals(customer)){
				items.add(u);
			}
		}
		
		return items;
	}

	@Override
	public List<Uitlening> alleUitleningen() {
		List<Uitlening> items = new ArrayList<Uitlening>();
		
		for(Uitlening u:uitleningen){
			items.add(u);
		}
		
		return items;
	}

	@Override
	public List<Uitlening> alleUitleningenVanCd() {
		List<Uitlening> Cds = new ArrayList<Uitlening>();
		
		for(Uitlening u : uitleningen){
			Item i = u.getUitgeleendItem();
			if(i instanceof Cd){
				Cds.add(u);
			}
		}
		
		return Cds;
	}

	@Override
	public List<Uitlening> alleUitleningenVanDvd() {
		List<Uitlening> dvds = new ArrayList<Uitlening>();
		
		for(Uitlening u : uitleningen){
			Item i = u.getUitgeleendItem();
			if(i instanceof Dvd){
				dvds.add(u);
			}
		}
		
		return dvds;
	}

	@Override
	public List<Uitlening> alleUitleningenVanGame() {
		List<Uitlening> games = new ArrayList<Uitlening>();
		
		for(Uitlening u : uitleningen){
			Item i = u.getUitgeleendItem();
			if(i instanceof Game){
				games.add(u);
			}
		}
		
		return games;
	}

	@Override
	public void uitleningVanEenItemStoppen(Uitlening uitlening) {
		uitlening.getUitgeleendItem().setUitgeleend(false);
		uitleningen.remove(uitlening);	
	}

	@Override
	public void uitleningVanMeerdereItemsStoppen(List<Uitlening> teStoppenItemlijst) {
		for(Uitlening u:teStoppenItemlijst){
			uitleningen.remove(u);
			u.getUitgeleendItem().setUitgeleend(false);
		}
	}

	@Override
	public DateTime geefEindDatumVanDeUitlening(Uitlening uitlening) {
		return uitlening.getBeginVerhuurDatum().plusDays(uitlening.getVerhuurPeriodeInDagen());
	}

	@Override
	public List<Uitlening> getAllUitleningen() {
		return uitleningen;
	}

}
