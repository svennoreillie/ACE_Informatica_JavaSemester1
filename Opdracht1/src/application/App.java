package application;

import model.V2.DateGreg;

public class App {

	public static void main(String[] args) throws Exception {

		// WORKING
		DateGreg test = new DateGreg();
		System.out.print(test.toString() + "\n");
		DateGreg test1 = new DateGreg();
		DateGreg test2 = new DateGreg("27/07/1990");
		System.out.print(test2.toString() + "\n");
		
		test2 = new DateGreg(27, 07, 1990);
		System.out.print(test2.toString() + "\n");
		
		
		DateGreg test3 = new DateGreg(test);
		System.out.print(test3.toString() + "\n");
		
		
		DateGreg test4 = new DateGreg("24/08/1998");
		System.out.print(test4.toString() + "\n\n");
		
		System.out.print(test.getFormatAmerican() + "\n");
		System.out.print(test.getFormatEuropean() + "\n\n");
		
		System.out.print(test.compareTo(test) + "\n");
		System.out.print(test.smallerThan(test2)  + "\n\n");
		
		// NOT WORKING
		System.out.print(test2.equals(test2) + "\n");
		System.out.print(test2.equals(test3) + "\n");
		
		//test.alterDate(aantalDagen);
		System.out.println("Date 1: " + test1.toString());
		System.out.println("Date 2: " + test2.toString());
		System.out.println("Difference in years: " + test1.differenceInYears(test2));
		System.out.println("Difference in months: " + test1.differenceInMonths(test2));
		System.out.println("Difference in days: " + test1.differenceInDays(test2));
	}
}
