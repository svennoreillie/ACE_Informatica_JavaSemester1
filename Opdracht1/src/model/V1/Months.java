package model.V1;

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
	    months.put(1, "januari");
		months.put(2, "februari");
		months.put(3, "maart");
		months.put(4, "april");
		months.put(5, "mei");
		months.put(6, "juni");
		months.put(7, "juli");
		months.put(8, "augustus");
		months.put(9, "september");
		months.put(10, "oktober");
		months.put(11, "november");
		months.put(12, "december");
		
	    monthDictionary = Collections.unmodifiableMap(months);
	}
	
	public static String getMonthName(int month) {
		return monthDictionary.get(month);
	}
	
}
