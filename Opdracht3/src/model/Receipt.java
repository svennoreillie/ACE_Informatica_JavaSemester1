package model;

import java.util.List;

public interface Receipt {
	public void describe();
	
	public List<Item> getItems();
	
	public double getTotal();
	
	public double getTaxes();
}
