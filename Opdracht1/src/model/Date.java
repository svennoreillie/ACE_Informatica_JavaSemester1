package model;

public interface Date {
	public boolean setDate(int day, int month, int year) throws Exception;
	

	
	public String getFormatAmerican();
	public String getFormatEuropean();
	
	public boolean smallerThan(Date d) throws Exception;
	public int differenceInYears (Date d) throws Exception;
	public int differenceInMonths (Date d) throws Exception;
	public int differenceInDays (Date d) throws Exception;
	public int totalDaysSinceJesus() throws Exception;
	public Date changeDate(int aantalDagen) throws Exception;
	public void alterDate(int aantalDagen) throws Exception;
	
	boolean equals(Object obj);
	int hashCode();

}