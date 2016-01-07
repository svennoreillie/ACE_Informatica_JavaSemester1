package model;

import java.util.List;

public class BodyDecorator extends ReceiptDecorator {

	public BodyDecorator(Receipt receipt) {
		super(receipt);
	}
	
	@Override
	public String getDescription() {
		String description = receipt.getDescription();
		String body="";
		
		for(Item i : getItems()){
			body+=i.getTitel() +"\t" +i.getVerhuurPrijsPerDag()+"\n";
		}
		body+="--------\n";
		
		return description+body;
		
		//System.out.println(body);
	}


	
}
