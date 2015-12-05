package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Customer;
import model.Item;
import model.Uitlening;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Games;

public class UitleenController implements UitleenService {
	
	List<Uitlening> uitleningen;
	
	
	public UitleenController(){
		uitleningen = new ArrayList<Uitlening>();
	}

	@Override
	public void aanmakenVanEenUitlening(Item item, Customer customer, int verhuurPeriodeDagen, Date beginVerhuurDatum) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> alleUitgeleendeItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cd> alleUitgeleendeCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dvd> alleUitgeleendeDvd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Games> alleUitgeleendeGames() {
		// TODO Auto-generated method stub
		return null;
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
	public Date geefEindDatumVanDeUitlening(Uitlening uitlening) {
		return null;
	}

}
