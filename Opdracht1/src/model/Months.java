package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Months {

	private static final Map<Integer, String> monthDictionary;

	public static Map<Integer, String> getMonthDictionary() {
		return monthDictionary;
	}

	static {
	    Map<Integer, String> months = new HashMap<Integer, String>();
	    months.put(1, "January");
		months.put(2, "February");
		months.put(3, "March");
		months.put(4, "April");
		months.put(5, "May");
		months.put(6, "June");
		months.put(7, "July");
		months.put(8, "August");
		months.put(9, "September");
		months.put(10, "October");
		months.put(11, "November");
		months.put(12, "December");
		
	    monthDictionary = Collections.unmodifiableMap(months);
	}
	
	public static String getMonthName(int month) {
		return monthDictionary.get(month);
	}
	
}
