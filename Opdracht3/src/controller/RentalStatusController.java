package controller;

import java.util.List;

import common.NotImplementedException;
import model.Item;
import model.Shop;

public class RentalStatusController {
	
	private Shop shop;
	
	public RentalStatusController(Shop shop) throws ControllerException{
		setShop(shop);
	}
	
	private void setShop(Shop shop) throws ControllerException{
		if(shop == null){
			throw new ControllerException("shop can't not be null!");
		}
		this.shop = shop;
	}
	
	public Shop getShop(){
		return this.shop;
	}
	
	public List<Item> getItems(){
		throw new NotImplementedException(); 
	}
	
	public List<Item> getRentedItems(){
		throw new NotImplementedException();
	}
}
