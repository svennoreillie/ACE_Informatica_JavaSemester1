package application;

import model.V2.DateGreg;;

public class App {

	public static void main(String[] args) throws Exception {

		////////////////////// WORKING //////////////////////
		/*
		// All constructors OK
		DateGreg test = new DateGreg();
		System.out.print(test.toString() + "\n");
		
		DateGreg test2 = new DateGreg(27, 07, 1990);
		System.out.print(test2.toString() + "\n");
		
		DateGreg test3 = new DateGreg(test);
		System.out.print(test3.toString() + "\n");
		
		DateGreg test4 = new DateGreg("24/08/1998");
		System.out.print(test4.toString() + "\n\n");
		
		
		// Methods
		System.out.print(test.getFormatAmerican() + "\n");
		System.out.print(test.getFormatEuropean() + "\n\n");
		
		System.out.print(test.compareTo(test) + "\n");
		System.out.print(test.smallerThan(test2)  + "\n\n");
		
		test.alterDate(-365);
		System.out.print(test.toString() + "\n");
		
		test.changeDate(365);
		System.out.print(test.toString() + "\n\n");
		
		System.out.print(test.compareTo(test2) + "\n\n");
		*/
		
		////////////////////// NOT WORKING //////////////////////
		
		//System.out.print(test2.equals(test2) + "\n");
		//System.out.print(test2.equals(test3) + "\n");
		
		
		DateGreg test5 = new DateGreg(29, 02, 2016);
		DateGreg test6 = new DateGreg("29/02/2016");
		DateGreg test7 = new DateGreg(01,01,2016);
		System.out.print(test5.toString() + "\n\n" + test6.toString() + "\n\n" + test7.leapTest());
	}
}
