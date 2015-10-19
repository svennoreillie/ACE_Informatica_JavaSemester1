package application;

import model.V2.DateGreg;

public class App {

	public static void main(String[] args) throws Exception {
		DateGreg test1 = new DateGreg();
		DateGreg test2 = new DateGreg("27/07/1990");
		
		System.out.println("Date 1: " + test1.toString());
		System.out.println("Date 2: " + test2.toString());
		System.out.println("Difference in years: " + test1.differenceInYears(test2));
		System.out.println("Difference in months: " + test1.differenceInMonths(test2));
		System.out.println("Difference in days: " + test1.differenceInDays(test2));
	}
}
