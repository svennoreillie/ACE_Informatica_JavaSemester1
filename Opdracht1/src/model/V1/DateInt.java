package model.V1;

import model.Date;
import model.DateBase;
import model.Months;


import java.util.Calendar;

import common.MagicStrings;


public class DateInt extends DateBase {

	//Region privates
	private int day = 1;
	private int month = 1;
	private int year = 1;
	private MagicStrings magicString = new MagicStrings(); 

	
	
	
	//Region constructors
	
	/**
	 * Instantiates an new DateInt object using the current system time.
	 * 
	 * @throws Exception
	 */
	public DateInt() throws Exception {
		//This is the standard constructor, nothing needs to happen here because privates have defaults of 1
		Calendar currentDate = Calendar.getInstance();
		this.setDate(currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.YEAR));
	}
	
	/**
	 * Instantiates a new DateInt object using the provided integers as parameters.
	 * 
	 * @param day Defines day of the month.
	 * @param month Defines the month.
	 * @param year Defines the year.
	 * @throws Exception
	 */
	public DateInt(int day, int month, int year) throws Exception {
		this.setDate(day, month, year);
	}
	
	/**
	 * Instantiates a new DateInt object by using an existing Date object.
	 * 
	 * @param date The Date object to base the new DateInt off of.
	 * @throws Exception Throws an exception if the provided Date cannot produce a correct string from the getFormatEuropean() method. This should not be possible, since the date's already been checked and set.
	 **/
	public DateInt(Date date) throws Exception {
		this(date.getFormatEuropean());
	}
	
	/**
	 * Instantiates a new DateInt object by using a string in the "DD/MM/YY" format.
	 * 
	 * @param date A string defining the date, in "DD/MM/YY" format.
	 * @throws Exception Should the provided string contain formatting errors, an error message is pulled from the MagicString class.
	 */
	public DateInt(String date) throws Exception {
		if (date == null) throw new Exception(magicString.getDateNull());
		if (date.length() != 10) throw new Exception(magicString.getDateLengthWrong());
		if (!date.contains("/")) throw new Exception(magicString.getDateSeperatorWrong());
		
		String[] strings=  date.split("/");
		
		if (strings.length != 3) throw new Exception(magicString.getDateFormatWrong());
		
		int day = Integer.parseInt(strings[0]);
		int month = Integer.parseInt(strings[1]);
		int year = Integer.parseInt(strings[2]);
		
		this.setDate(day, month, year);
	}
	
	
	
	//Region properties
	//No getDateInt available so I changed these to public PVE
	/**
	 * Provides public access to the current object's day field.
	 * 
	 * @return An integer representing this object's day
	 * @throws Exception
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Provides public access to the current object's month field.
	 * 
	 * @return An integer representing this object's month
	 * @throws Exception
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Provides public access to the current object's year field.
	 * 
	 * @return An integer representing this object's year
	 * @throws Exception
	 */
	public int getYear() {
		return year;
	}
	
	
	
	
	//Region public methods from Date interface
	/**
	 * Changes the current object's date fields.
	 * 
	 *  @param day The new date's day of the month
	 *  @param month The new date's month
	 *  @param year The new date's year
	 *  @throws Exception
	 *  @see checkDate
	 */
	@Override
	public boolean setDate(int day, int month, int year) throws Exception {
		if (year < 1) throw new Exception(magicString.getYearRangeWrong());
		if (month < 1 || month > 12) throw new Exception(magicString.getMonthRangeWrong());
		if (day < 1 || day > getNumberOfDays(month, year)) throw new Exception(magicString.getDayRangeWrong());
		
		this.day = day;
		this.month = month;
		this.year = year;
		
		return true;
	}
	
	/**
	 * Converts a date to American format
	 * 
	 * @return a string of a date in MM/DD/YY format
	 */
	@Override
	public String getFormatAmerican() {
		String format = String.format("%04d/%02d/%02d", this.year, this.month, this.day);
		return format;
	}

	/**
	 * Converts a date to European format
	 * 
	 * @return a string of a date in DD/MM/YY format
	 */
	@Override
	public String getFormatEuropean() {
		return String.format("%02d/%02d/%04d", this.day, this.month, this.year);
	}
	
	/**
	 * Counts the number of days elapsed between 01/01/0001 and the present date.
	 * 
	 * @return an integer representing the number of days since 01/01/0001
	 */
	public int totalDaysSinceJesus() throws Exception {
		try {
			int total = 0;
			total += ((this.year - 1) * 365);
			
			//calculate leapDays
			int numberOfLeapYears = (this.year - 1) / 4;
			//subtract centuries (no leapyears)
			int numberOfCenturies = (this.year - 1) / 100;
			//subtract 400's (exception on the centuries leapyears)
			int numberOf400s = (this.year - 1) / 400;
			int totalLeapDays = numberOfLeapYears - numberOfCenturies + numberOf400s;
			
			total += totalLeapDays;
			
			for (int i = 1; i < this.month; i++) {
				total += getNumberOfDays(i, this.year);
			}
			
			total += this.day;
			
			return total;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Compares the current date object to the provided Date parameter. If the Date precedes DateGreg, this method returns a true statement.
	 * 
	 * @param d The date to compare this DateGreg object to
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean smallerThan(Date d) throws Exception {
		return this.totalDaysSinceJesus() < d.totalDaysSinceJesus();
	}

	/**
	 * Calculates the difference in years between the current DateInt object and the provided Date object.
	 * 
	 * @param d The date to which DateGreg needs to be compared to
	 * @return An unsigned integer representing the number of years between both dates
	 */
	@Override
	public int differenceInYears(Date d) throws Exception {
		try {
			DateInt date = differenceDate(d);
			
			return (date.year - 1);
		} catch (Exception e) {
			if (e.getMessage() == MagicStrings.datesEqualError) return 0;
			throw e;
		} 
	}

	@Override
	public int differenceInMonths(Date d) throws Exception {
		try {
			DateInt date = differenceDate(d);
			
			return ((date.year - 1) * 12) + date.month;
		} catch (Exception e) {
			if (e.getMessage() == MagicStrings.datesEqualError) return 0;
			throw e;
		}
	}

	@Override
	public int differenceInDays(Date d) throws Exception {
		return Math.abs(d.totalDaysSinceJesus() - this.totalDaysSinceJesus());
	}

	@Override
	public Date changeDate(int aantalDagen) throws Exception {
		int nieuwAantalDagen = this.totalDaysSinceJesus() + aantalDagen;
		if (nieuwAantalDagen < 0) {
			throw new Exception(magicString.getDateZero());
		}
	    return this.createDate(nieuwAantalDagen);
	}
	
	@Override
	public void alterDate(int aantalDagen) throws Exception {
		int nieuwAantalDagen = this.totalDaysSinceJesus() + aantalDagen;
		if (nieuwAantalDagen < 0) {
			throw new Exception(magicString.getDateZero());
		}
		DateInt newDate = this.createDate(nieuwAantalDagen);
	    this.setDate(newDate.day, newDate.month, newDate.year);
	}
	
	//Region publics from base
	@Override
	public String toString() {
		return String.format("%02d %s %04d", this.day, Months.getMonthName(this.month), this.year);
	}
	
	//Region helpers
	private int getNumberOfDays(int month, int year) throws Exception {
		return getNumberOfDays(month, year, false);
	}
	
	private int getNumberOfDays(int month, int year, Boolean skipCalcLeapYears) throws Exception {
		if (month < 1 || month > 12) throw new Exception(magicString.getMonthRangeWrong());
		if (month == 2 && year < 1) throw new Exception(magicString.getYearRangeWrong());
		
		int[] longMonths = new int[] { 1, 3, 5, 7, 8, 10, 12 };
		
		if (!skipCalcLeapYears) {
			if (month == 2) {
				//February => search for leap years
				if (isLeapYear(year)) {
					return 29;
				} 
				
				return 28;
			} 
		}
		
		for (int i : longMonths) {
			if (i == month) return 31;
		}
		
		return 30;
	}
	
	private int getNumberOfDays(int year) throws Exception {
		if (year < 1) throw new Exception(magicString.getYearRangeWrong());
		if (isLeapYear(year)) return 366;
		return 365;
	}

	private boolean isLeapYear(int year) {
		Boolean deelbaarDoor4 = (year % 4) == 0;
		Boolean deelbaarDoor100 = (year % 100) == 0;
		Boolean deelbaarDoor400 = (year % 400) == 0;
		if (!deelbaarDoor4) return false;
		if (deelbaarDoor400) return true;
		if (deelbaarDoor100) return false;
		return true;
	}
	
	private DateInt differenceDate(Date d) throws Exception {
		int totalDays = this.totalDaysSinceJesus();
		int otherTotalDays = d.totalDaysSinceJesus();
		
		int difference = Math.abs(otherTotalDays - totalDays);
		if (difference == 0) throw new Exception(MagicStrings.datesEqualError);
		DateInt date = this.createDate(difference);
		return date;
	}
	
	private DateInt createDate(int totalDays) throws Exception {
		int year = 1;
		while (totalDays > getNumberOfDays(year)) {
			totalDays -= getNumberOfDays(year);
			year++;
		}
		
		int month = 1;
		while (totalDays > getNumberOfDays(month, year)) {
			totalDays -= getNumberOfDays(month, year);
			month++;
		}
		
		int day = totalDays;
		return new DateInt(day, month, year);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

}
