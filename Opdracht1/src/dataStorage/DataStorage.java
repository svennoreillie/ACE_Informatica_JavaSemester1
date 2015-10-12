package dataStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.V1.MagicStrings;

public class DataStorage implements DataStorageInterface {
	
	
	private String fullList = new String();
	private List<String> reservationList = new ArrayList<String>();
	private File file = new File("bestanden\\werknemers.txt");
	
	
	@Override
	public String getData() {
		try{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()){
				fullList+=(scanner.nextLine());
				fullList+=";";
			}
			if (scanner!=null){
				  scanner.close();
				}
		}
		catch(FileNotFoundException ex){
			MagicStrings s = new MagicStrings ();
		    System.out.println(s.getFileNotfound());
		  }
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return fullList;
	}

	//List van Strings met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe
	@Override
	public List<String> getReservationList() {
		try{
			DataStorage storage = new DataStorage();
			fullList = storage.getData();
			String fullStringList = fullList.toString();
			String [] splitList = fullStringList.split(";");
			for ( String s : splitList){
				reservationList.add(s);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return reservationList;
	}

	//Schrijvt volledige string (overschrijft hard)
	@Override
	public void setData(String data) {
		try{
			PrintWriter writer = new PrintWriter(file);
			writer.print(data);
			writer.close();
		}
		catch(FileNotFoundException ex){
			MagicStrings s = new MagicStrings ();
		    System.out.println(s.getFileNotfound());
		  }
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	//List van Strings met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe  => roept setdata aan
	@Override
	public void setReservationList(List<String> reservationList) {
		try{
			fullList = new String();
			for( String s : reservationList){
				fullList += s;
			}
			DataStorage storage = new DataStorage();
			storage.setData(fullList);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	//Voegt een reservatie toe in vorm: 111,5/11/2015,4>Adams,Chloe   =>  voegt toe aan interne list stringbuffer en doet setdata
	@Override
	public void addReservation(String reservation) {
		// TODO gebruiken van dataFormatCheck
		try{
			DataStorage storage = new DataStorage();
			fullList = storage.getData();
			fullList += ";";
			fullList += reservation;
			storage.setData(fullList);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	//Controleert of ingevoerde data correct is
	@Override
	public boolean dataFormatCheck(String data) {
		// TODO
		return false;
		
	}

}
