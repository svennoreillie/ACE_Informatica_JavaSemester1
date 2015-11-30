package controller;

import model.Shop;

public class ButtonPanelController {

	Shop shop;
	
	public ButtonPanelController(Shop shop) {
		setShop(shop);
	}
	
	public void setShop(Shop shop){
		this.shop = shop;
	}
	
	public Shop getShop(){
		return shop;
	}

}
