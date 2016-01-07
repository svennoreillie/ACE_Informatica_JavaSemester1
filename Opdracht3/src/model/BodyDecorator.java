package model;

public class BodyDecorator extends ReceiptDecorator {

	public BodyDecorator(Receipt receipt) {
		super(receipt);
	}
	
	@Override
	public void describe() {
		receipt.describe();
		System.out.println("This is the body");
		
	}

	
}
