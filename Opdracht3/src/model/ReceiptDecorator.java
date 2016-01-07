package model;

import java.util.List;

public abstract class ReceiptDecorator implements Receipt{

	Receipt receipt;
	
	public ReceiptDecorator(Receipt receipt){
		this.receipt = receipt;
	}
	
	@Override
	public List<Item> getItems(){
		return receipt.getItems();
	}
	
	@Override
	public double getTotal() {
		return receipt.getTotal();
	}

	@Override
	public double getTaxes() {
		return receipt.getTaxes();
	}
	
}
