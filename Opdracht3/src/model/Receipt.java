package model;

import java.util.List;

public interface Receipt {
	public String getDescription();
	
	public List<Item> getItems();
	
	public double getTotal();
	
	public double getTaxes();
}
