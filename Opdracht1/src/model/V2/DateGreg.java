package model.V2;

import model.Date;
import model.DateBase;

import java.util.Calendar;
import java.util.GregorianCalendar;

import common.MagicStrings;

/**
 * DateGreg is a subclass of Date, serving as a wrapper class for the GregorianCalendar class. The provided dates are to be manipulated using GregorianCalendar functions wherever possible.
 */
public class DateGreg extends DateBase {
	
	// TODO Nice to have: use alterDate() in differenceInYear() for efficiency 
	// TODO Nice to have: documentation
	
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
	 * @return boolean
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
	 * Calculates the difference in years between the current DateGreg object and the provided Date object.
	 * 
	 * @param d The date to which DateGreg needs to be compared to
	 * @return An unsigned integer representing the number of years between both dates
	 */
	@Override
	public int differenceInYears(Date d) throws Exception {
		return Math.abs(this.getYear() - ((DateGreg) d).getYear());
	}

	/**
	 * Calculates the difference in months between the current DateGreg object and the provided Date object.
	 * 
	 * @param d The date to which DateGreg needs to be compared to
	 * @return An integer representing the number of months between both dates
	 */
	@Override
	public int differenceInMonths(Date d) throws Exception {
		DateGreg smallest;
		DateGreg biggest;
		
		if (this.smallerThan(d)){
			smallest = new DateGreg(this);
			biggest = new DateGreg(d);
		}
		else{
			smallest = new DateGreg(d);
			biggest = new DateGreg(this);
		}
		return ((this.differenceInYears(d)-1)*12) + Math.abs(12 - smallest.getMonth()) + (biggest.getMonth());
	}
	
	/**
	 *  Calculates the difference in months between the current DateGreg object and the provided Date object.
	 *  
	 *  @param d The date to which DateGreg needs to be compared to
	 *  @return An integer representing the number of days elapsed between both dates
	 */
	@Override
	public int differenceInDays(Date d) throws Exception {
		int days = 0;
		DateGreg smallest;
		DateGreg biggest;
		
		// Determine in which order dates should be placed in calculations
		if (this.smallerThan(d)){
			smallest = new DateGreg(this);
			biggest = new DateGreg(d);
		}
		else{
			smallest = new DateGreg(d);
			biggest = new DateGreg(this);
		}
		

		// If there is a difference of more than 24 months between two dates, we can safely add the whole years inbetween to our day total
		if (this.differenceInMonths(d) > 24){
			days += (this.differenceInYears(d) - 1) * 365;
			
			//then add one extra day per leap year
			for (int ly = smallest.getYear() + 1; ly < biggest.getYear(); ly++){
				if (smallest.Greg.isLeapYear(ly)){
					days++;
				}
			}
		}
		
		// If there's more than 12 months between two dates, it means new year falls somewhere between both; meaning we can calculate the difference between that fixed date between both,
		// and add it to our day total.
		if ((this.differenceInMonths(d) > 12)){
			DateGreg smallCalc = new DateGreg(smallest);
			for (int i = smallest.getMonth() +1 ; i <= 12; i++){
				smallCalc.Greg.set(Calendar.MONTH, i);
				days += smallCalc.Greg.getActualMaximum(Calendar.DATE);
			}

			DateGreg bigCalc = new DateGreg(biggest);
			for (int i = 1; i < biggest.getMonth(); i++){
				bigCalc.Greg.set(Calendar.MONTH, i);
				days += bigCalc.Greg.getActualMaximum(Calendar.DATE);
			}
		}
		
		// If there's more than a month between both dates, we can count from the smaller date up until its month's end; and the start of the biggest date's month up until its day.
		if ((this.differenceInMonths(d) > 1)){
			if (this.differenceInMonths(d) < 12){
				DateGreg monthCalc = new DateGreg(smallest);
				for (int m = smallest.getMonth(); m < biggest.getMonth() -1; m++){
					monthCalc.Greg.set(Calendar.MONTH, m+1);
					days += monthCalc.Greg.getActualMaximum(Calendar.DATE);
				}
			}
			days += (smallest.Greg.getActualMaximum(Calendar.DATE)) - smallest.getDay();
			days += biggest.getDay();
		}
		
		// If there's strictly less than a month's difference between two days, we can just subtract them from one another
		if (this.differenceInMonths(d) < 1){
			if (smallest.getMonth() != biggest.getMonth()){
				days += (smallest.Greg.getActualMaximum(Calendar.DATE)) - smallest.getDay();
				days += biggest.getDay();
			}
			else{
				days += biggest.getDay() - smallest.getDay();
			}
		}
		
		return days;
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

	/**
	 * Compares the current date object to the provided Date, 
	 * 
	 * @param o The object to compare this DateGreg object to
	 */
	//TODO
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
		DateGreg d = new DateGreg(this);
		d.Greg.add(Calendar.DATE, numberOfDays);
		return d;
	}
	
	
	//////////////////////////////////////////////////////////////////////// HELPER ////////////////////////////////////////////////////////////////////////
	/**
	 * Checks if the provided date is valid or not. If not, an error message is fetched from the MagicStrings class, and thrown as an exception.
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
		
		GregorianCalendar leapTest = new GregorianCalendar(year, (month-1), 1);
		if (day > leapTest.getActualMaximum(Calendar.DATE)){
			throw new Exception(String.format("The month of %1$tB %1$tY counts " + leapTest.getActualMaximum(Calendar.DATE) + " days.", leapTest));
		}
	}

	@Override
	public int totalDaysSinceJesus() throws Exception {
		// Not in use for DateGreg
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Greg == null) ? 0 : Greg.hashCode());
		result = prime * result + ((magicString == null) ? 0 : magicString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateGreg other = (DateGreg) obj;
		if (Greg == null) {
			if (other.Greg != null)
				return false;
		} else if (!Greg.equals(other.Greg))
			return false;
		if (magicString == null) {
			if (other.magicString != null)
				return false;
		} else if (!magicString.equals(other.magicString))
			return false;
		return true;
	}
}
