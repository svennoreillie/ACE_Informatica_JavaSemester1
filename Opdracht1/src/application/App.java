package application;

import model.V2.DateGreg;

public class App {

	public static void main(String[] args) throws Exception {
	
		DateGreg test = new DateGreg();
		
		System.out.print(test.getDay() + "/" + test.getMonth() + "/" + test.getYear());
		
	}
}
