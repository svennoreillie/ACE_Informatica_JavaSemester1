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
	public DateInt() throws Exception {
		//This is the standard constructor, nothing needs to happen here because privates have defaults of 1
		Calendar currentDate = Calendar.getInstance();
		this.setDate(currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.YEAR));
	}
	
	public DateInt(int day, int month, int year) throws Exception {
		this.setDate(day, month, year);
	}
	
	public DateInt(Date date) throws Exception {
		this(date.getFormatEuropean());
	}
	
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
	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
	
	
	
	//Region public methods from Date interface
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
			int numberOfLeapYears = this.year / 4;
			//subtract centuries (no leapyears)
			int numberOfCenturies = this.year / 100;
			//subtract 400's (exception on the centuries leapyears)
			int numberOf400s = this.year / 400;
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

	@Override
	public boolean smallerThan(Date d) throws Exception {
		return this.totalDaysSinceJesus() < d.totalDaysSinceJesus();
	}

	@Override
	public int differenceInYears(Date d) throws Exception {
		DateInt date = differenceDate(d);
		
		return (date.year - 1); 
	}

	@Override
	public int differenceInMonths(Date d) throws Exception {
		DateInt date = differenceDate(d);
		
		return ((date.year - 1) * 12) + date.month;
	}

	@Override
	public int differenceInDays(Date d) throws Exception {
		return d.totalDaysSinceJesus() - this.totalDaysSinceJesus();
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
		if (month < 1 || month > 12) throw new Exception(magicString.getMonthRangeWrong());
		if (month == 2 && year < 1) throw new Exception(magicString.getYearRangeWrong());
		
		int[] longMonths = new int[] { 1, 3, 5, 7, 8, 10, 12 };
		
		if (month == 2) {
			//February => search for leap years
			if (isLeapYear(year)) {
				return 29;
			} 
			
			return 28;
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
		if ((year % 400) != 0) return true;
		if ((year % 100) != 0) return false;
		if ((year % 4) != 0) return false;	
		
		return true;
	}
	
	private DateInt differenceDate(Date d) throws Exception {
		int totalDays = this.totalDaysSinceJesus();
		int otherTotalDays = d.totalDaysSinceJesus();
		
		int difference = otherTotalDays - totalDays;
		DateInt date = this.createDate(difference);
		return date;
	}
	
	private DateInt createDate(int totalDays) throws Exception {
		int year = 1;
		while (totalDays >= getNumberOfDays(year)) {
			totalDays -= getNumberOfDays(year);
			year++;
		}
		
		int month = 1;
		while (totalDays >= getNumberOfDays(month, year)) {
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
