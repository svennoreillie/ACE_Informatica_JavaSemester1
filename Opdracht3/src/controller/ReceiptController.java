package controller;

import java.math.BigDecimal;

import model.Receipt;

public class ReceiptController {
	
	public static void printReceipt(Receipt receipt){
		//System.out.println(getHeader(receipt));
		//System.out.println(getFooter(receipt));
	}
	
	/*private static String getHeader(Receipt receipt){
		String header="";
		header += receipt .getReceiptNumber()+"\n";
		header += receipt.getDate().getDayOfMonth() + "/" + receipt.getDate().getMonthOfYear() + "/" + receipt.getDate().getYear()+"\n";
		header += receipt.getDate().getHourOfDay()+":"+receipt.getDate().getMinuteOfHour()+"\n";
		header += "openingsUren";
		return header;
	} 
	
	private static String getFooter(Receipt receipt){
		String footer = "";
		BigDecimal tax = receipt.getPriceTaxExcl().divide(new BigDecimal(100)).multiply(new BigDecimal(21));
		footer += "netto: " + receipt.getPriceTaxExcl() + "\u20ac\tTax: "+tax+"\u20ac\n" ;
		footer += "mededeling\n";
		footer += "vaste mededeling\n";
		return footer;
	}*/
}
