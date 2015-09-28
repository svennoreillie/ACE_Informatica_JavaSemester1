package model;

public abstract class DateBase implements Date {

	@Override
	public abstract boolean setDate(int day, int month, int year) throws Exception;

	@Override
	public abstract String getFormatAmerican();

	@Override
	public abstract String getFormatEuropean();

	@Override
	public abstract boolean smallerThan(Date d) throws Exception;

	@Override
	public abstract int differenceInYears(Date d) throws Exception;

	@Override
	public abstract int differenceInMonths(Date d) throws Exception;

	@Override
	public abstract int differenceInDays(Date d) throws Exception;

	@Override
	public abstract int totalDaysSinceJesus() throws Exception;
	
	@Override
	public abstract Date changeDate(int aantalDagen) throws Exception;
	
	@Override
	public abstract void alterDate(int aantalDagen) throws Exception;

	//Extra methods
	@Override
	public abstract String toString();
	
	@Override
	public abstract boolean equals(Object o);
	
	public abstract int compareTo(Date otherDate) throws Exception;
}
