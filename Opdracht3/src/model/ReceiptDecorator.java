package model;

public abstract class ReceiptDecorator implements Receipt{

	Receipt receipt;
	
	public ReceiptDecorator(Receipt receipt){
		this.receipt = receipt;
	}
	
}
