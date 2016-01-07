package model;

import java.util.List;

public class BodyDecorator extends ReceiptDecorator {

	public BodyDecorator(Receipt receipt) {
		super(receipt);
	}
	
	@Override
	public void describe() {
		receipt.describe();
		String body="";
		
		for(Item i : getItems()){
			body+=i.getTitel() +"\t" +i.getVerhuurPrijsPerDag()+"\n";
		}
		
		System.out.println(body);
	}


	
}
