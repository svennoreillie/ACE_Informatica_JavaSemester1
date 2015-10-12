package model.V2;

import model.Date;
import model.DateBase;
import model.V1.MagicStrings;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * DateGreg is a subclass of Date, serving as a wrapper class for the GregorianCalendar class. The provided dates are to be manipulated using GregorianCalendar functions wherever possible.
 * 
 * @author André Nóbrega
 * @author Bart Janssens
 */
public class DateGreg extends DateBase {
	
	// TODO difference in years/months/days
	// TODO implement the Exception strings
	
	// PRIVATE VARIABLE INSTANCES //
	private GregorianCalendar Greg;
	private MagicStrings magicString = new MagicStrings();
	
	// CONSTRUCTORS //
	
	/**
	 * Instantiates a new DateGreg object using the current system time.
	 * 
	 * @throws Exception
	 */
	public DateGreg() throws Exception{
		try{
			Greg = new GregorianCalendar();
		}
		catch (Exception e){
			throw e;
		}
	};
	
	/**
	 * Instantiates a new DateGreg object using the provided integers as parameters.
	 * 
	 * @param day Defines day of the month.
	 * @param month Defines the month.
	 * @param year Defines the year.
	 * @throws Exception
	 */
	public DateGreg(int day, int month, int year) throws Exception{
		checkDate(day, month, year);		
		Greg = new GregorianCalendar(year, month, day);
	};
	
	/**
	 * Instantiates a new DateGreg object by using a string in the "DD/MM/YY" format.
	 * 
	 * @param date A string defining the date, in "DD/MM/YY" format.
	 * @throws Exception Should the provided string contain formatting errors, an error message is pulled from the MagicString class.
	 */
	public DateGreg(String date) throws Exception
	{
		try{
			if (date == null) throw new Exception(magicString.getDateNull());
			if (date.length() != 10) throw new Exception(magicString.getDateLengthWrong());
			if (!date.contains("/")) throw new Exception(magicString.getDateSeperatorWrong());
			
			String[] strings=  date.split("/");
			
			if (strings.length != 3) throw new Exception(magicString.getDateFormatWrong());
			
			int day = Integer.parseInt(strings[0]);
			int month = Integer.parseInt(strings[1]);
			int year = Integer.parseInt(strings[2]);
			// Array starts with 0 index value!
			
			checkDate(day, month, year);
			Greg = new GregorianCalendar(year, month, day);

		}
		catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * Instantiates a new DateGreg object by using an existing Date object.
	 * 
	 * @param date The Date object to base the new DateGregg off of.
	 * @throws Exception Throws an exception if the provided Date cannot provide a correct string from the getFormatEuropean(); should not be technically possible.
	 */
	public DateGreg(Date date) throws Exception{
		try{
			String dateString = date.getFormatEuropean();
			String[] strings=  dateString.split("/");
			if (strings.length != 3) throw new Exception(magicString.getDateFormatWrong());
			
			int day = Integer.parseInt(strings[0]);
			int month = Integer.parseInt(strings[1]);
			int year = Integer.parseInt(strings[2]);
			
			checkDate(day, month, year);
			Greg = new GregorianCalendar(year, month, day);
		}
		catch (Exception e){
			throw e;
		}
	};
	
	// GETTERS //
	/**
	 * Provides public access to the current object's day field.
	 * 
	 * @return An integer representing this object's day
	 * @throws Exception
	 */
	public int getDay() throws Exception{
		return this.Greg.get(GregorianCalendar.DATE);
	}
	
	/**
	 * Provides public access to the current object's month field. Note that months in Calendar are kept in a zero-based array, hence the need to add +1 to the integer it returns.
	 * 
	 * @return An integer representing this object's month
	 * @throws Exception
	 */
	public int getMonth() throws Exception{
		return (this.Greg.get(GregorianCalendar.MONTH) + 1);
	}
	
	/**
	 * Provides public access to the current object's year field.
	 * 
	 * @return An integer representing this object's year
	 * @throws Exception
	 */
	public int getYear() throws Exception{
		return this.Greg.get(GregorianCalendar.YEAR);
	}
	
	// OVERRIDES //
	/**
	 * Changes the current object's date fields. 
	 */
	@Override
	public boolean setDate(int day, int month, int year) throws Exception {
		try{
			checkDate(day, month, year);
			Greg.set(year, month, day);
			
			return true;
		}
		catch (Exception e){
			throw e;
		}
	}

	/**
	 * Returns the current date as a string in the YY/MM/DD format.
	 */
	@Override
	public String getFormatAmerican() {
		return String.format("%1$tY/%1$tm/%1$te", this.Greg);
	}

	/**
	 * Returns the current date as a string in the DD/MM/YY format.
	 */
	@Override
	public String getFormatEuropean() {

		return String.format("%1$te/%1$tm/%1$tY", this.Greg);
	}

	/**
	 * Compares the current date object to the provided Date parameter. If the Date precedes DateGreg, this method returns a true statement.
	 */
	@Override
	public boolean smallerThan(Date d) throws Exception {
		DateGreg otherDate = new DateGreg(d);
		if ((Greg.compareTo(otherDate.Greg)) == -1){
			return true;
		}
		else{
			return false;
		}
		//Opmerking: als je 2 identieke datums ingeeft, krijg je FALSE als resultaat (null-velden worden ingevuld met het huidige systeemtijd?)
	}
	
	/**
	 * Compares the current date object to the provided Object. If both are equal, this method returns a true statement.
	 */
	@Override
	public boolean equals(Object o) {
		if (this.Greg.equals(o)){
			return true;
		}
		else{
			return false;
		}
		// Geeft telkens een FALSE terug; ook al vergelijk je een object met zichzelf
	}

	@Override
	public int differenceInYears(Date d) throws Exception {
		DateGreg toCompare = new DateGreg(d);
		
		/*
		int diff = toCompare.Greg.get(Calendar.YEAR) - this.Greg.get(Calendar.YEAR);
		if (this.Greg.get(Calendar.MONTH) > toCompare.Greg.get(Calendar.MONTH) ||
				(this.Greg.get(Calendar.MONTH) == toCompare.Greg.get(Calendar.MONTH) && this.Greg.get(Calendar.DATE) > toCompare.Greg.get(Calendar.DATE))){
			diff--;
		}		
		
		return diff;
		*/
		
		
		return 0;
	}

	@Override
	public int differenceInMonths(Date d) throws Exception {
		//DateGreg toCompare = new DateGreg(d);
		// TODO differenceInMonths
		
		//int diff = (differenceInYears(d) * 12) + ();
		
		/*
		if (this.Greg.get(Calendar.MONTH) > toCompare.Greg.get(Calendar.MONTH) ||
				(this.Greg.get(Calendar.MONTH) == toCompare.Greg.get(Calendar.MONTH) && this.Greg.get(Calendar.DATE) > toCompare.Greg.get(Calendar.DATE))){
			diff--;
		}
		*/
		
		//return diff;
		
		return 0;
	}

	@Override
	public int differenceInDays(Date d) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalDaysSinceJesus() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Returns this object's current date as a string; e.g. "24 July 2006".
	 */
	@Override
	public String toString() {
		//return String.format("%i2 %s %i4", this.Greg.get(Calendar.DATE), Months.getMonthName(this.Greg.get(Calendar.MONTH)), this.Greg.get(Calendar.YEAR));
		// Geeft blijkbaar problemen met het converteren van Int naar String
		
		/*
		StringBuilder sb = new StringBuilder();
		sb.append(this.Greg.get(Calendar.DATE) + " ");
		sb.append(Months.getMonthName(this.Greg.get(Calendar.MONTH) + 1) + " ");
		sb.append(this.Greg.get(Calendar.YEAR));
		
		String dateToString = new String(sb);
		return dateToString;
		*/
		// Werkt degelijk, maar maakt 2 variabelen aan
		
		return String.format("%1$te %1$tB %1$tY", this.Greg);
		// Geeft maand terug volgens systeemtaal 	
	}

	@Override
	public int compareTo(Date otherDate) throws Exception {
		DateGreg toCompare = new DateGreg(otherDate);
		
		return Greg.compareTo(toCompare.Greg);
	}

	/**
	 * 
	 */
	@Override
	public void alterDate(int aantalDagen) throws Exception {
		this.Greg.add(Calendar.DATE, aantalDagen);
	}

	@Override
	public Date changeDate(int aantalDagen) throws Exception{
		// TODO Check if changeDate() method gives desired result
		this.Greg.add(Calendar.DATE, aantalDagen);
		return this;
	}
	
	// HELPER
	public void checkDate(int day, int month, int year) throws Exception{
		if (day < 1 || day > 31) throw new Exception(magicString.getDayRangeWrong());
		if (month < 1 || month > 12) throw new Exception(magicString.getMonthRangeWrong());
		if (year < 1) throw new Exception(magicString.getDateZero());
		if (year > 9999) throw new Exception(magicString.getYearRangeWrong());
	}
	
	// for testing purposes
	public Boolean leapTest() throws Exception{
		return this.Greg.isLeapYear(this.getYear());
	}
}
