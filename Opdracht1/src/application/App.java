package application;

import model.V2.DateGreg;

public class App {

	public static void main(String[] args) throws Exception {
		DateGreg test1 = new DateGreg();
		DateGreg test2 = new DateGreg("18/10/2016");
		
		System.out.println(test1.differenceInDays(test2));
	}
}
