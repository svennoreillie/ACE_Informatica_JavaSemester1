<<<<<<< HEAD
package model;

import org.joda.time.DateTime;

/**
 * 
 * @author Huybrechts
 *
 */

public class HeaderDecorator extends ReceiptDecorator{

	DateTime date;
	String bonNummer;
	String openingsuren;
	
	
	public HeaderDecorator(Receipt receipt) {
		super(receipt);
		date = DateTime.now();
		bonNummer = "" + date.getYear() + date.getMonthOfYear() + date.getDayOfMonth() + date.getHourOfDay() + date.getMinuteOfHour() + date.getSecondOfMinute();
		openingsuren = "Ma - Vr: 08:00 -> 18:00";
	}
	
	@Override
	public String getDescription() {
		String description = receipt.getDescription();
		return description + getHeader();
	}
	
	private String getHeader(){
		String header = "";
		header +=  date.getDayOfMonth() + "/" + date.getMonthOfYear() + "/" + date.getYear()+"\n";
		header += date.getHourOfDay() + ":" + date.getMinuteOfHour() + ":" + date.getSecondOfMinute()+"\n";
		header += "--------\n";
		
		
		return header;
	}




}
=======
package model;

import org.joda.time.DateTime;

public class HeaderDecorator extends ReceiptDecorator{

	DateTime date;
	String bonNummer;
	String openingsuren;
	
	
	public HeaderDecorator(Receipt receipt) {
		super(receipt);
		date = DateTime.now();
		bonNummer = "" + date.getYear() + date.getMonthOfYear() + date.getDayOfMonth() + date.getHourOfDay() + date.getMinuteOfHour() + date.getSecondOfMinute();
		openingsuren = "Ma - Vr: 08:00 -> 18:00";
	}
	
	@Override
	public String getDescription() {
		String description = receipt.getDescription();
		return description + getHeader();
	}
	
	private String getHeader(){
		String header = "";
		header +=  date.getDayOfMonth() + "/" + date.getMonthOfYear() + "/" + date.getYear()+"\n";
		header += date.getHourOfDay() + ":" + date.getMinuteOfHour() + ":" + date.getSecondOfMinute()+"\n";
		header += "--------\n";
		
		
		return header;
	}




}
>>>>>>> refs/remotes/svennoreillie/development
