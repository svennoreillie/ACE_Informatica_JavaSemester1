package controller;

import java.util.List;

import common.NotImplementedException;
import model.Item;
import model.Shop;

public class RentalStatusController {
	
	private Shop shop;
	
	public RentalStatusController(Shop shop){
		setShop(shop);
	}
	
	private void setShop(Shop shop){
		/*if(shop == null){
			throw new ControllerException("shop can't not be null!");
		}*/
		this.shop = shop;
	}
	
	public Shop getShop(){
		return this.shop;
	}
	
	public List<Item> getItems(){
		return shop.getItems();
	}
	
	public List<Item> getRentedItems(){
		throw new NotImplementedException();
	}
}
