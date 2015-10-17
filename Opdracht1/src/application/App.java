package application;

import model.V2.DateGreg;
import model.V1.DateInt;

public class App {

	public static void main(String[] args) throws Exception {
		DateGreg test = new DateGreg();
		DateGreg test2 = new DateGreg("17/02/2016");
		
		System.out.println(test.differenceInYears(test2));
		System.out.println(test.differenceInMonths(test2));
		System.out.println(test.differenceInDays(test2));

	}
}
