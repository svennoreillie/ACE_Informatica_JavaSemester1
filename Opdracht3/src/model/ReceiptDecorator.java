package model;

import java.util.List;

/**
 * 
 * @author Huybrechts
 *
 */

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
