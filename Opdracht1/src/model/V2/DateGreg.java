package model.V2;

import model.Date;
import model.DateBase;
import model.Months;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateGreg extends DateBase {
	
	//TODO: difference in years/months/days
	
	//private variable instances
	
	private int day = 1;
	private int month = 1;
	private int year = 1;
	private GregorianCalendar Greg = new GregorianCalendar();
	
	//CONSTRUCTORS
	public DateGreg() throws Exception{
		try{
			this.day = Greg.get(Calendar.DAY_OF_MONTH);
			this.month = Greg.get(Calendar.MONTH);
			this.year = Greg.get(Calendar.YEAR);
		}
		catch (Exception e){
			throw e;
		}
		
		this.setDate(day, month, year);
	};
	
	public DateGreg(int year, int month, int dayOfMonth) throws Exception{
		this.day = dayOfMonth;
		this.month = month;
		this.year = year;
		
		this.setDate(dayOfMonth, month, year);
	};
	
	public DateGreg(String date) throws Exception
	{
		if (date == null) throw new Exception("Date was null");
		if (date.length() != 10) throw new Exception("Incorrect date length, you must supply date in following format DD/MM/YYYY");
		if (!date.contains("/")) throw new Exception("Date does not contain the correct separator");
		
		String[] strings=  date.split("/");
		
		if (strings.length != 3) throw new Exception("Did not find all datesegments. Check if date is in following format DD/MM/YYYY");
		
		int day = Integer.parseInt(strings[1]);
		int month = Integer.parseInt(strings[2]);
		int year = Integer.parseInt(strings[3]);
		
		this.setDate(day, month, year);
		
		//wat als het om Amerikaans formaat gaat?
	}
	
	public DateGreg(Date date) throws Exception{
		this(date.getFormatEuropean());
	};
	
	//Overrides
	@Override
	public boolean setDate(int day, int month, int year) throws Exception {
		if (year < 1 || year > 9999) throw new Exception("Year is not in a valid range");
		if (month < 1 || month > 12) throw new Exception("Month is not in a valid range");
		if (day < 1 || day > getNumberOfDays(month, year)) throw new Exception("Day is not in a valid range");
		
		
		this.day = day;
		this.month = month;
		this.year = year;
		
		return true;
	}

	@Override
	public String getFormatAmerican() {
		return String.format("%i4/%i2/%i2", this.year, this.month, this.day);
	}

	@Override
	public String getFormatEuropean() {
		return String.format("%i2/%i2/%i4", this.day, this.month, this.year);
	}

	@Override
	public boolean smallerThan(Date d) throws Exception {
		Greg.set(this.year, this.month, this.day);
		
		DateGreg convertToCompare = new DateGreg(d);
		GregorianCalendar toCompare = new GregorianCalendar(convertToCompare.year, convertToCompare.month, convertToCompare.year);
		
		if ((Greg.compareTo(toCompare)) < 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		Greg.set(this.year, this.month, this.day);
		
		if (Greg.equals(o)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public int differenceInYears(Date d) throws Exception {
		Greg.set(this.year, this.month, this.day);
		
		DateGreg convertToCompare = new DateGreg(d);
		GregorianCalendar toCompare = new GregorianCalendar(convertToCompare.year, convertToCompare.month, convertToCompare.year);
		
		return Greg.compareTo(toCompare);
		
		// 2 opmerkingen: het is niet duidelijk wat voor int je terug krijgt van compareTo();
		// en de constructor GregorianCalendar(Date) doet het nog steeds niet
	}

	@Override
	public int differenceInMonths(Date d) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int differenceInDays(Date d) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalDaysSinceJesus() throws Exception {
		try {
			int total = 0;
			total += ((this.year - 1) * 365);
			
			//calculate leapDays
			int numberOfLeapYears = this.year / 4;
			//subtract centuries (no leap years)
			int numberOfCenturies = this.year / 100;
			int totalLeapDays = numberOfLeapYears - numberOfCenturies;
			
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
	public String toString() {
		return String.format("%i2 %s %i4", this.day, Months.getMonthName(this.month), this.year);
	}

	@Override
	public int compareTo(Date otherDate) throws Exception {
		Greg.set(this.year, this.month, this.day);
		
		DateGreg toConvert = new DateGreg(otherDate);
		GregorianCalendar toCompare = new GregorianCalendar(toConvert.year, toConvert.month, toConvert.day);
		
		return Greg.compareTo(toCompare);
	}

	@Override
	public void alterDate(int aantalDagen) throws Exception {
		GregorianCalendar dateToChange = new GregorianCalendar(this.year, this.month, this.day);
		dateToChange.add(dateToChange.DAY_OF_MONTH, aantalDagen);
		
		setDate(dateToChange.get(day), dateToChange.get(month), dateToChange.get(year));
	}

	@Override
	public Date changeDate(int aantalDagen) throws Exception{
		
		GregorianCalendar dateToChange = new GregorianCalendar(this.year, this.month, this.day);
		dateToChange.add(dateToChange.DAY_OF_MONTH, aantalDagen);
		
		setDate(dateToChange.get(day), dateToChange.get(month), dateToChange.get(year));
		return this;
		
		//moeten we een nieuw Date-object geven, of de bestaande wijzigen?
	}
	
	//Region helper
	
	private int getNumberOfDays(int month, int year){
		int numberOfDays = 0; 
		GregorianCalendar nOD = new GregorianCalendar(year, month, 1);
		
		final int[] daysPerMonth =
			{ 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		numberOfDays = daysPerMonth[month];
		
		if (month == 2){
			if (nOD.isLeapYear(year)){	
			numberOfDays = 29;
			}
			else{
				numberOfDays = 28;
			}
		}
		
		return numberOfDays;
	}
}
