package model;

import model.V1.DateInt;

public class DateFactory {

	
	public static Date generateDate() throws Exception {
		return new DateInt();
	}
	
	public static Date generateDate(String date) throws Exception {
		return new DateInt(date);
	}
	
	public static Date generateDate(Date date) throws Exception {
		return new DateInt(date);
	}
	
	public static Date generateDate(int day, int month, int year) throws Exception {
		return new DateInt(day, month, year);
	}
	
	
}
