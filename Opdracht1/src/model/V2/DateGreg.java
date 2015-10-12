package model.V2;

import model.Date;
import model.DateBase;
import model.V1.MagicStrings;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * DateGreg is a subclass of Date, serving as a wrapper class for the GregorianCalendar class. The provided dates are to be manipulated using GregorianCalendar functions wherever possible.
 */
public class DateGreg extends DateBase {
	
	// TODO difference in years/months/days
	
	//////////////////////////////////////////////////////////////////////// PRIVATE VARIABLE INSTANCES /////////////////////////////////////////////////////////////////////////
	private GregorianCalendar Greg;
	private MagicStrings magicString = new MagicStrings();
	
	
	//////////////////////////////////////////////////////////////////////// CONSTRUCTORS ////////////////////////////////////////////////////////////////////////
	
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
	 * @see checkDate
	 */
	public DateGreg(int day, int month, int year) throws Exception{
		checkDate(day, month, year);		
		Greg = new GregorianCalendar(year, month-1, day);
	};
	
	/**
	 * Instantiates a new DateGreg object by using a string in the "DD/MM/YY" format.
	 * 
	 * @param date A string defining the date, in "DD/MM/YY" format.
	 * @throws Exception Should the provided string contain formatting errors, an error message is pulled from the MagicString class.
	 * 
	 * @see checkDate
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
			Greg = new GregorianCalendar(year, month-1, day);

		}
		catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * Instantiates a new DateGreg object by using an existing Date object.
	 * 
	 * @param date The Date object to base the new DateGregg off of.
	 * @throws Exception Throws an exception if the provided Date cannot provide a correct string from the getFormatEuropean(); which should not be possible, since the date's already been checked and set.
	 * @see checkDate
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
			Greg = new GregorianCalendar(year, month-1, day);
		}
		catch (Exception e){
			throw e;
		}
	};
	
	
	//////////////////////////////////////////////////////////////////////// GETTERS ////////////////////////////////////////////////////////////////////////
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
		return (this.Greg.get(GregorianCalendar.MONTH) +1);
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
	
	
	//////////////////////////////////////////////////////////////////////// OVERRIDES ////////////////////////////////////////////////////////////////////////
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
	 * 
	 * @param d The date to compare this DateGreg object to
	 * @throws Exception
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
	 * 
	 * @param o The object to compare this DateGreg object to
	 */

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

	// TODO
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
	
	// TODO
	@Override
	public int differenceInDays(Date d) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	// TODO
	@Override
	public int totalDaysSinceJesus() throws Exception {
		try {
			int total = 0;
			int daysInMonth = this.Greg.getActualMaximum(Calendar.MONTH);
			total += ((this.getYear() - 1) * 365);
			
			//calculate leapDays
			int numberOfLeapYears = this.getYear() / 4;
			//subtract centuries (no leapyears)
			int numberOfCenturies = this.getYear() / 100;
			//subtract 400's (exception on the centuries leapyears)
			int numberOf400s = this.getYear() / 400;
			int totalLeapDays = numberOfLeapYears - numberOfCenturies + numberOf400s;
			
			total += totalLeapDays;
			
			for (int i = 1; i < this.getMonth(); i++) {
				total += daysInMonth;
			}
			
			total += this.getDay();
			
			return total;
		} catch (Exception e) {
			throw e;
		}
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

	// TODO
	@Override
	public int compareTo(Date otherDate) throws Exception {
		DateGreg toCompare = new DateGreg(otherDate);
		
		return Greg.compareTo(toCompare.Greg);
	}

	/**
	 * Changes the current DateGreg object, by adding or subtracting a number of days to its date value. Inserting a positive integer pushes the date forward, while a negative integer subtracts from it.
	 * 
	 * @param numberOfDays The number of days by which the date needs to be modified.
	 */
	@Override
	public void alterDate(int numberOfDays) throws Exception {
		this.Greg.add(Calendar.DATE, numberOfDays);
	}

	/**
	 * Creates a new DateGreg object, by adding or subtracting a number of days to the current DateGreg object's date value. Inserting a positive integer pushes the date forward, while a negative integer subtracts from it.
	 * 
	 * @param numberOfDays The number of days by which the date needs to be modified.
	 */
	@Override
	public Date changeDate(int numberOfDays) throws Exception{
		// TODO Check if changeDate() method gives desired result
		this.Greg.add(Calendar.DATE, numberOfDays);
		return this;
	}
	
	
	//////////////////////////////////////////////////////////////////////// HELPER ////////////////////////////////////////////////////////////////////////
	/**
	 * Checks if the provided date values fall within the expected parameters. If not, an error message is fetched from the MagicStrings class, and thrown as an exception.
	 * 
	 * @param day The provided day of the month. Should range from 1 to max. 31.
	 * @param month The provided month. Should range from 1 to 12.
	 * @param year The provided year. For this application's purposes, only A.D. dates are accepted, up to the year 9999.
	 * @throws Exception
	 */
	public void checkDate(int day, int month, int year) throws Exception{
		if (day < 1 || day > 31) throw new Exception(magicString.getDayRangeWrong());
		if (month < 1 || month > 12) throw new Exception(magicString.getMonthRangeWrong());
		if (year < 1) throw new Exception(magicString.getDateZero());
		if (year > 9999) throw new Exception(magicString.getYearRangeWrong());
		
		// what about leap years? Should we let GregorianCalendar sort that out, or throw an exception?
	}
	
	// for testing purposes
	public Boolean leapTest() throws Exception{
		return this.Greg.isLeapYear(this.getYear());
	}
}
