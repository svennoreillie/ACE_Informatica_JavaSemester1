package common;

public final class MagicStrings {
	
	public final String dateNull = "Date was null";
	public final String dateLengthWrong = "Incorrect date length, you must supply date in following format DD/MM/YYYY";
	public final String dateSeperatorWrong = "Date does not contain the correct separator";
	public final String dateFormatWrong = "Did not find all datesegments. Check if date is in following format DD/MM/YYYY";
	public final String yearRangeWrong = "Year is not in a valid range";
	public final String monthRangeWrong = "Month is not in a valid range";
	public final String dayRangeWrong = "Day is not in a valid range";
	public final String dateZero = "Date can't go below 01/01/0001";
	public final String fileNotfound = "File not found!";
	
	public static final String reservationNotFound = "Reservation not found!";
	public static final String houseNotFound = "House not found!";
	public static final String personNotFound = "Person not found!";
	public static final String reservationDateError = "The provided date is not correct";
	public static final String reservationAvailabilityError = "The provided date is no longer available";
	public static final String datesEqualError = "Dates are equal, a returnDate can not be created";
	

	
	// constructor
	public MagicStrings() {
	}
	
	public String getDateNull() {
		return dateNull;
	}
	
	public String getDateLengthWrong () {
		return dateLengthWrong;
	}
	
	public String getDateSeperatorWrong () {
		return dateSeperatorWrong;
	}
	
	public String getDateFormatWrong () {
		return dateFormatWrong;
	}
	
	public String getYearRangeWrong () {
		return yearRangeWrong;
	}
	
	public String getMonthRangeWrong () {
		return monthRangeWrong;
	}
	
	public String getDayRangeWrong () {
		return dayRangeWrong;
	}
	
	public String getDateZero () {
		return dateZero;
	}

	public String getFileNotfound () {
		return fileNotfound;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateFormatWrong == null) ? 0 : dateFormatWrong.hashCode());
		result = prime * result + ((dateLengthWrong == null) ? 0 : dateLengthWrong.hashCode());
		result = prime * result + ((dateNull == null) ? 0 : dateNull.hashCode());
		result = prime * result + ((dateSeperatorWrong == null) ? 0 : dateSeperatorWrong.hashCode());
		result = prime * result + ((dateZero == null) ? 0 : dateZero.hashCode());
		result = prime * result + ((dayRangeWrong == null) ? 0 : dayRangeWrong.hashCode());
		result = prime * result + ((fileNotfound == null) ? 0 : fileNotfound.hashCode());
		result = prime * result + ((monthRangeWrong == null) ? 0 : monthRangeWrong.hashCode());
		result = prime * result + ((yearRangeWrong == null) ? 0 : yearRangeWrong.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MagicStrings other = (MagicStrings) obj;
		if (dateFormatWrong == null) {
			if (other.dateFormatWrong != null)
				return false;
		} else if (!dateFormatWrong.equals(other.dateFormatWrong))
			return false;
		if (dateLengthWrong == null) {
			if (other.dateLengthWrong != null)
				return false;
		} else if (!dateLengthWrong.equals(other.dateLengthWrong))
			return false;
		if (dateNull == null) {
			if (other.dateNull != null)
				return false;
		} else if (!dateNull.equals(other.dateNull))
			return false;
		if (dateSeperatorWrong == null) {
			if (other.dateSeperatorWrong != null)
				return false;
		} else if (!dateSeperatorWrong.equals(other.dateSeperatorWrong))
			return false;
		if (dateZero == null) {
			if (other.dateZero != null)
				return false;
		} else if (!dateZero.equals(other.dateZero))
			return false;
		if (dayRangeWrong == null) {
			if (other.dayRangeWrong != null)
				return false;
		} else if (!dayRangeWrong.equals(other.dayRangeWrong))
			return false;
		if (fileNotfound == null) {
			if (other.fileNotfound != null)
				return false;
		} else if (!fileNotfound.equals(other.fileNotfound))
			return false;
		if (monthRangeWrong == null) {
			if (other.monthRangeWrong != null)
				return false;
		} else if (!monthRangeWrong.equals(other.monthRangeWrong))
			return false;
		if (yearRangeWrong == null) {
			if (other.yearRangeWrong != null)
				return false;
		} else if (!yearRangeWrong.equals(other.yearRangeWrong))
			return false;
		return true;
	}
}
	

