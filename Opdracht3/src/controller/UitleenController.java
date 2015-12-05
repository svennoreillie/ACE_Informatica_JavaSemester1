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

public class UitleenController implements UitleenService {
	
	List<Uitlening> uitleningen;
	
	
	public UitleenController(){
		uitleningen = new ArrayList<Uitlening>();
	}

	@Override
	public void aanmakenVanEenUitlening(Item item, Customer customer, int verhuurPeriodeDagen, DateTime beginVerhuurDatum) throws ControllerException {
		if(beginVerhuurDatum.isBeforeNow()){
			throw new ControllerException("Date can't be in the past");
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
