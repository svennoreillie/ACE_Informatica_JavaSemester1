package model.V1;

import model.Date;
import model.DateBase;

public class DateInt extends DateBase {

	private int day = 1;
	private int month = 1;
	private int year = 1;

	
	protected int getDay() {
		return day;
	}

	protected int getMonth() {
		return month;
	}

	protected int getYear() {
		return year;
	}
	
	
	public DateInt() {
	}
	
	public DateInt(int day, int month, int year) throws Exception {
		setDate(day, month, year);
	}
	
	public DateInt(Date date) {
		this(date.getFormatEuropean());
	}
	
	public DateInt(String date) {
		//Todo
		/*
		  	In deze String zit de datum in DDMMJJJJ formaat maar tussen de dag, maand en jaar staat een scheidingsteken (Vb 12/05/2009)
			Opmerking: maand moet ingegeven worden met 2 cijfers (Vb 05), jaartal met 4 cijfers (Vb 0567), dagnr met 1 of 2 cijfers
			De constructoren moeten een geldigheidscontrole uitvoeren en eventueel een Exception werpen. 
		 */
		throw new UnsupportedOperationException();
	}
	
	
	@Override
	public boolean setDate(int day, int month, int year) throws Exception {
		if (year < 1) throw new Exception("Year is not in a valid range");
		if (month < 1 || month > 12) throw new Exception("Month is not in a valid range");
		if (day < 1 || day > getNumberOfDays(month, year)) throw new Exception("Day is not in a valid range");
		
		this.day = day;
		this.month = month;
		this.year = year;
		
		return true;
	}
	
	private int getNumberOfDays(int month, int year) throws Exception {
		if (month < 1 || month > 12) throw new Exception("Month is not in a valid range");
		if (month == 2 && year < 1) throw new Exception("Year is not in a valid range");
		
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
		if (year < 1) throw new Exception("Year is not in a valid range");
		if (isLeapYear(year)) return 366;
		return 365;
	}

	private boolean isLeapYear(int year) {
		if ((year % 4) != 0) return false;
		if ((year % 100) != 0) return false;
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
	
	public int totalDaysSinceJesus() throws Exception {
		try {
			int total = 0;
			total += ((this.year - 1) * 365);
			
			//calculate leapDays
			int numberOfLeapYears = this.year / 4;
			//subtract centuries (no leapyears)
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

	private DateInt differenceDate(Date d) throws Exception {
		int totalDays = this.totalDaysSinceJesus();
		int otherTotalDays = d.totalDaysSinceJesus();
		
		int difference = otherTotalDays - totalDays;
		DateInt date = this.createDate(difference);
		return date;
	}

	@Override
	public int differenceInDays(Date d) throws Exception {
		return d.totalDaysSinceJesus() - this.totalDaysSinceJesus();
	}

	@Override
	public Date changeDate(int aantalDagen) throws Exception {
		int nieuwAantalDagen = this.totalDaysSinceJesus() + aantalDagen;
		if (nieuwAantalDagen < 0) {
			throw new Exception("Date can't go below 01/01/0001");
		}
	    return this.createDate(nieuwAantalDagen);
	}
	
	@Override
	public void alterDate(int aantalDagen) throws Exception {
		int nieuwAantalDagen = this.totalDaysSinceJesus() + aantalDagen;
		if (nieuwAantalDagen < 0) {
			throw new Exception("Date can't go below 01/01/0001");
		}
		DateInt newDate = this.createDate(nieuwAantalDagen);
	    this.setDate(newDate.day, newDate.month, newDate.year);
	}

	@Override
	public String toString() {
		return String.format("%i2 %s %i4", this.day, Months.getMonthName(this.month), this.year);
	}

	
	@Override
    public boolean equals(Object obj) {
       if (!(obj instanceof DateInt))
            return false;
        if (obj == this)
            return true;

        DateInt other = (DateInt) obj;
        return Integer.compare(this.day, other.day) == 0
	        && Integer.compare(this.month, other.month) == 0
	        && Integer.compare(this.year, other.year) == 0;
        
    }


	@Override
	public int compareTo(Date otherDate) throws Exception {
		if (otherDate == null) throw new NullPointerException("otherDate is null");
		
		int thisDays = this.totalDaysSinceJesus();
		int otherDays = otherDate.totalDaysSinceJesus();
		return Integer.compare(thisDays, otherDays);
	}

}
