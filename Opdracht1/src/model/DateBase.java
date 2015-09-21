package model;

public abstract class DateBase implements Date {

	@Override
	public abstract boolean setDate(int day, int month, int year);

	@Override
	public abstract String getFormatAmerican();

	@Override
	public abstract String getFormatEuropean();

	@Override
	public abstract boolean smallerThan(Date d);

	@Override
	public abstract int differenceInYears(Date d);

	@Override
	public abstract int differenceInMonths(Date d);

	@Override
	public abstract int differenceInDays(Date d);

	@Override
	public abstract Date changeDate(int aantalDagen);

	//Extra methods
	@Override
	public abstract String toString();
	
	@Override
	public abstract boolean equals(Object o);
	
	public abstract int compareTo(Date otherDate);
}
