package model;

import java.util.List;

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
	public void describe() {
		receipt.describe();
		header();
	}
	
	public void header(){
		String header = "";
		header +=  date.getDayOfMonth() + "/" + date.getMonthOfYear() + "/" + date.getYear()+"\n";
		header += date.getHourOfDay() + ":" + date.getMinuteOfHour() + ":" + date.getSecondOfMinute();
		
		
		System.out.println(header);
	}




}
