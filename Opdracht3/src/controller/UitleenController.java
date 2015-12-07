package controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

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
		if(Integer.parseInt(beginVerhuurDatum.year().getAsString())<Integer.parseInt(DateTime.now().year().getAsString())||
				Integer.parseInt(beginVerhuurDatum.monthOfYear().getAsString())<Integer.parseInt(DateTime.now().monthOfYear().getAsString())||
				Integer.parseInt(beginVerhuurDatum.dayOfMonth().getAsString())<Integer.parseInt(DateTime.now().dayOfMonth().getAsString())
				){
			throw new ControllerException("Date can't be in the past");
		}
		
		//Checks if verhuurPeriodeDagen is positive
		if(verhuurPeriodeDagen<0){
			throw new ControllerException("The number of days can't be negative");
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
						throw new ControllerException("The item is already rented during the entered period");
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
	public List<Item> uitgeleendeItemsVanHuidigeKlant(Customer customer) {
		List<Item> items = new ArrayList<Item>();
		
		for(Uitlening u : uitleningen){
			if(u.getKlantDieUitleent().equals(customer)){
				items.add(u.getUitgeleendItem());
			}
		}
		
		return items;
	}

	@Override
	public List<Item> alleUitgeleendeItems() {
		List<Item> items = new ArrayList<>();
		
		for(Uitlening u:uitleningen){
			items.add(u.getUitgeleendItem());
		}
		
		return items;
	}

	@Override
	public List<Cd> alleUitgeleendeCd() {
		List<Cd> Cds = new ArrayList<Cd>();
		
		for(Uitlening u : uitleningen){
			Item i = u.getUitgeleendItem();
			if(i instanceof Cd){
				Cd c = (Cd) i;
				Cds.add(c);
			}
		}
		
		return Cds;
	}

	@Override
	public List<Dvd> alleUitgeleendeDvd() {
		List<Dvd> dvds = new ArrayList<Dvd>();
		
		for(Uitlening u : uitleningen){
			Item i = u.getUitgeleendItem();
			if(i instanceof Dvd){
				Dvd d = (Dvd) i;
				dvds.add(d);
			}
		}
		
		return dvds;
	}

	@Override
	public List<Game> alleUitgeleendeGames() {
		List<Game> games = new ArrayList<Game>();
		
		for(Uitlening u : uitleningen){
			Item i = u.getUitgeleendItem();
			if(i instanceof Game){
				Game g = (Game) i;
				games.add(g);
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

}
