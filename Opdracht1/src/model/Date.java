package model;

public interface Date {
	public boolean setDate(int day, int month, int year);
	
	public String getFormatAmerican();
	public String getFormatEuropean();
	
	public boolean smallerThan(Date d);
	public int differenceInYears (Date d);
	public int differenceInMonths (Date d);
	public int differenceInDays (Date d);
	public Date changeDate(int aantalDagen);
}