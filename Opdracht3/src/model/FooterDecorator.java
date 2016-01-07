package model;

import java.util.List;

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
		String footer="";
		
		footer+=getTotal()+"\n";
		footer+=getTaxes()+"\n";
		footer+="Volgende zondag zijn wij open van 8.00 tot 12.00\nDank u voor je aankoop";
		
		System.out.println(footer);
	}

	

}
