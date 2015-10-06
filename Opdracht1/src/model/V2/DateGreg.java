package model.V2;

import model.Date;
import model.DateBase;
import model.V2.Months;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateGreg extends DateBase {
	
	// TODO difference in years/months/days
	// TODO rework class by removing private day/month/year ints
	// TODO add proper gets/sets 
	// TODO use the Exception strings
	
	// PRIVATE VARIABLE INSTANCES //
	private GregorianCalendar Greg;
	
	// CONSTRUCTORS //
	public DateGreg() throws Exception{
		try{
			Greg = new GregorianCalendar();
		}
		catch (Exception e){
			throw e;
		}
	};
	
	public DateGreg(int day, int month, int year) throws Exception{
		try{
			Greg = new GregorianCalendar(year, month, day);
		}
		catch (Exception e){
			throw e;
		}
	};
	
	public DateGreg(String date) throws Exception
	{
		try{
			if (date == null) throw new Exception("Date was null");
			if (date.length() != 10) throw new Exception("Incorrect date length, you must supply date in following format DD/MM/YYYY");
			if (!date.contains("/")) throw new Exception("Date does not contain the correct separator");
			
			String[] strings=  date.split("/");
			
			if (strings.length != 3) throw new Exception("Did not find all datesegments. Check if date is in following format DD/MM/YYYY");
			
			int day = Integer.parseInt(strings[1]);
			int month = Integer.parseInt(strings[2]);
			int year = Integer.parseInt(strings[3]);
			
			Greg = new GregorianCalendar(year, month, day);
			
			//wat als het om Amerikaans formaat gaat?
		}
		catch (Exception e){
			throw e;
		}
	}
	
	public DateGreg(Date date) throws Exception{
		String dateString = date.getFormatEuropean();
		String[] strings=  dateString.split("/");
		if (strings.length != 3) throw new Exception("Did not find all datesegments. Check if date is in following format DD/MM/YYYY");
		
		int day = Integer.parseInt(strings[1]);
		int month = Integer.parseInt(strings[2]);
		int year = Integer.parseInt(strings[3]);
		
		Greg = new GregorianCalendar(year, month, day);
	};
	
	// GETTERS //
	public int getDay() throws Exception{
		return this.Greg.get(GregorianCalendar.DATE);
	}
	
	public int getMonth() throws Exception{
		int month = this.Greg.get(GregorianCalendar.MONTH);
		return month +1;
		
		// It is necessary to add +1 to the Calendar's month, since it's zero-based. E.g.: April = 3 instead of 4 
		// PS: WHY?!!!?11!?
	}
	
	public int getYear() throws Exception{
		return this.Greg.get(GregorianCalendar.YEAR);
	}
	
	// OVERRIDES //
	@Override
	public boolean setDate(int day, int month, int year) throws Exception {
		Greg.set(year, month, day);
		
		return true;
	}

	@Override
	public String getFormatAmerican() {
		return String.format("%i4/%i2/%i2", Greg.get(Calendar.YEAR), Greg.get(Calendar.MONTH), Greg.get(Calendar.DAY_OF_MONTH));
	}

	@Override
	public String getFormatEuropean() {
		return String.format("%i2/%i2/%i4", Greg.get(Calendar.DAY_OF_MONTH), Greg.get(Calendar.MONTH), Greg.get(Calendar.YEAR));
	}

	@Override
	public boolean smallerThan(Date d) throws Exception {
		DateGreg otherDate = new DateGreg(d);
		if ((Greg.compareTo(otherDate.Greg)) < 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (Greg.equals(o)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public int differenceInYears(Date d) throws Exception {
		DateGreg toCompare = new DateGreg(d);
		
		int diff = toCompare.Greg.get(Calendar.YEAR) - this.Greg.get(Calendar.YEAR);
		if (this.Greg.get(Calendar.MONTH) > toCompare.Greg.get(Calendar.MONTH) ||
				(this.Greg.get(Calendar.MONTH) == toCompare.Greg.get(Calendar.MONTH) && this.Greg.get(Calendar.DATE) > toCompare.Greg.get(Calendar.DATE))){
			diff--;
		}		
				
		return diff;
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

	// TO DO fix the toString() method
	@Override
	public String toString() {
		//return String.format("%i2 %s %i4", this.getDay(), Months.getMonthName(this.getMonth()), this.getYear());
		
		return "STATUS: FUBAR!!!";
	}

	@Override
	public int compareTo(Date otherDate) throws Exception {
		DateGreg toCompare = new DateGreg(otherDate);
		
		return Greg.compareTo(toCompare.Greg);
	}

	@Override
	public void alterDate(int aantalDagen) throws Exception {
		this.Greg.add(Calendar.DATE, aantalDagen);
	}

	@Override
	public Date changeDate(int aantalDagen) throws Exception{
		// TODO Check if changeDate() method is correct
		this.Greg.add(Calendar.DATE, aantalDagen);
		return this;
	}
	
	
}
