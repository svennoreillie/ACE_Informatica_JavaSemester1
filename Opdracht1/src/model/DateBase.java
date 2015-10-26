package model;

public abstract class DateBase implements Date {
	
	
	public abstract int getDay() throws Exception;
	
	public abstract int getMonth() throws Exception;
	
	public abstract int getYear() throws Exception;
	
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
    public boolean equals(Object obj)  {
       if (!DateBase.class.isAssignableFrom(obj.getClass()))
            return false;
        if (obj == this)
            return true;

        Date other = (Date) obj;
        try {
			return Integer.compare(this.totalDaysSinceJesus(), other.totalDaysSinceJesus()) == 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        
    }
	
	@Override
	public abstract int hashCode();

	public int compareTo(Date otherDate) throws Exception {
		if (otherDate == null) throw new NullPointerException("otherDate is null");
		
		int thisDays = this.totalDaysSinceJesus();
		int otherDays = otherDate.totalDaysSinceJesus();
		return Integer.compare(thisDays, otherDays);
	}
	
	
}
