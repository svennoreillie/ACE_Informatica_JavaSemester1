package application;

import java.util.Scanner;
import model.V2.*;

public class App {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		DateGreg d1;
		//DateGreg d2;
		String datum1;
		
		System.out.print("Please enter a date: ");
		datum1 = input.nextLine();
		
		try {
			d1 = new DateGreg(datum1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(d1.toString());
		
		input.close();
	}
}
