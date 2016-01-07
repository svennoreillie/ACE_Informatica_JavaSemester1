package model;

public class HeaderDecorator extends ReceiptDecorator{

	public HeaderDecorator(Receipt receipt) {
		super(receipt);
	}
	
	@Override
	public void describe() {
		receipt.describe();
		header();
	}
	
	public void header(){
		System.out.println("this is the header");
	}

}
