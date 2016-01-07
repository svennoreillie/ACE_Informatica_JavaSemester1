package model;

public class FooterDecorator extends ReceiptDecorator{

	public FooterDecorator(Receipt receipt) {
		super(receipt);
	}
	
	@Override
	public void describe() {
		receipt.describe();
		footer();
	}
	
	public void footer(){
		System.out.println("this is the footer");
	}

}
