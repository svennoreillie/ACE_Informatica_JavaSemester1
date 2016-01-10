
package model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import controller.BoeteController;

/**
 * 
 * @author Huybrechts
 *
 */

public class ConcreteReceipt implements Receipt {
		
	List<Uitlening> uitleningen;
	double total;
	
	public ConcreteReceipt() {
		items = new ArrayList<>();
	}
	
	public void setItems(List<Uitlening> uitleningen){
		
		if(uitleningen == null){
			throw new IllegalArgumentException("items can't be null");
			
		}
		
		this.uitleningen = uitleningen;
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public List<Uitlening> getUitleningen() {
		return uitleningen;
	}
	
	public List<Item> getItems(){
		List<Item> items = new ArrayList<>();
		for(Uitlening u:getUitleningen()){
			items.add(u.getUitgeleendItem());
		}
		return items;
	}

	@Override
	public double getTotal() {
		double total = 0;
		
		for(Uitlening u : getUitleningen()){
			Item item = u.getUitgeleendItem();
			total+=item.getVerhuurPrijsPerDag()*u.getVerhuurPeriodeInDagen();
			if(u.getBeginVerhuurDatum().plusDays(u.getVerhuurPeriodeInDagen()).isAfterNow()){
				Duration duration = new Duration(DateTime.now(),u.getBeginVerhuurDatum().plusDays(u.getVerhuurPeriodeInDagen()));
				duration.getStandardDays();
				total+= new BoeteController().berekenBoeteWaarde(u).doubleValue();
			}
		}
		
		return total;
	}
	

	@Override
	public double getTaxes() {
		double taxes;
		taxes = getTotal()*0.21;
		return taxes;
	}

}