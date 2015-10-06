package model.V1;

public final class MagicStrings {
	
	private static String 	dateNull,dateLengthWrong,dateSeperatorWrong,dateFormatWrong,
							yearRangeWrong,monthRangeWrong,dayRangeWrong,dateZero;
	
	// constructor
	public MagicStrings() {
		dateNull = new String("Date was null");
		dateLengthWrong = new String("Incorrect date length, you must supply date in following format DD/MM/YYYY");
		dateSeperatorWrong = new String("Date does not contain the correct separator");
		dateFormatWrong = new String("Did not find all datesegments. Check if date is in following format DD/MM/YYYY");
		yearRangeWrong = new String("Year is not in a valid range");
		monthRangeWrong = new String("Month is not in a valid range");
		dayRangeWrong = new String("Day is not in a valid range");
		dateZero = new String("Date can't go below 01/01/0001");
	}
	
	public String getDateNull() {
		return dateNull;
	}
	
	public String getDateLengthWrong () {
		return dateLengthWrong;
	}
	
	public String getDateSeperatorWrong () {
		return dateSeperatorWrong;
	}
	
	public String getDateFormatWrong () {
		return dateFormatWrong;
	}
	
	public String getYearRangeWrong () {
		return yearRangeWrong;
	}
	
	public String getMonthRangeWrong () {
		return monthRangeWrong;
	}
	
	public String getDayRangeWrong () {
		return dayRangeWrong;
	}
	
	public String getDateZero () {
		return dateZero;
	}

}
	

