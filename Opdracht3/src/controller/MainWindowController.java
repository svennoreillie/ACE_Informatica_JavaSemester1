package controller;

import model.Shop;

public class MainWindowController {
	Shop shop;
	
	public MainWindowController(Shop shop){
		setShop(shop);
	}

	private void setShop(Shop shop){
		this.shop = shop;
	}
	
	public Shop getShop(){
		return this.shop;
	}
}
