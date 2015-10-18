package application;

import model.V2.DateGreg;
import model.V1.DateInt;

public class App {

	public static void main(String[] args) throws Exception {
		// WORKING
		DateGreg test = new DateGreg();
		System.out.print(test.toString() + "\n");
		
		
		DateGreg test2 = new DateGreg(27, 07, 1990);
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
	}
}
