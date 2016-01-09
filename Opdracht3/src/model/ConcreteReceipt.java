package model;

import java.util.ArrayList;
import java.util.List;

public class ConcreteReceipt implements Receipt {
		
	List<Item> items;
	double total;
	
	public ConcreteReceipt() {
		items = new ArrayList<>();
	}
	
	public void setItems(List<Item> items){
		
		if(items == null){
			throw new IllegalArgumentException("items can't be null");
			
		}
		
		this.items = items;
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public List<Item> getItems() {
		return items;
	}

	@Override
	public double getTotal() {
		double total = 0;
		
		for(Item i : getItems()){
			total+=i.getVerhuurPrijsPerDag();
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
