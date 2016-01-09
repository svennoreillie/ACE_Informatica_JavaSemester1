package model;

/**
 * 
 * @author Huybrechts
 *
 */

public class FooterDecorator extends ReceiptDecorator{

	public FooterDecorator(Receipt receipt) {
		super(receipt);
	}
	
	@Override
	public String getDescription() {
		return receipt.getDescription()+ getFooter();
	}
	
	public String getFooter(){
		String footer="";
		
		footer+="Totaal: "+getTotal()+"\n";
		footer+="Btw: "+getTaxes()+"\n";
		footer+="Volgende zondag zijn wij open van 8.00 tot 12.00\nDank u voor je aankoop";
		
		return footer;
	}

	

}
