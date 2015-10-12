package model.V1;

public final class MagicStrings {
	
	public final String dateNull = "Date was null";
	public final String dateLengthWrong = "Incorrect date length, you must supply date in following format DD/MM/YYYY";
	public final String dateSeperatorWrong = "Date does not contain the correct separator";
	public final String dateFormatWrong = "Did not find all datesegments. Check if date is in following format DD/MM/YYYY";
	public final String yearRangeWrong = "Year is not in a valid range";
	public final String monthRangeWrong = "Month is not in a valid range";
	public final String dayRangeWrong = "Day is not in a valid range";
	public final String dateZero = "Date can't go below 01/01/0001";
	public final String fileNotfound = "Bestand niet gevonden";
	
	// constructor
	public MagicStrings() {
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

	public String getFileNotfound () {
		return fileNotfound;
	}
}
	

