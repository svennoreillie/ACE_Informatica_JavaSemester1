package dataStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.MagicStrings;
import model.V1.DateInt;


public class DataStorage implements DataStorageInterface {
	
	
	private String fullList = new String();
	private List<String> reservationList = new ArrayList<String>();
	private File file = new File("bestanden\\werknemers.txt");
	private DataStorage storage = new DataStorage();
	private MagicStrings ms = new MagicStrings ();
	
	@Override
	public String getData() throws Throwable {
		try{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()){
				fullList+=(scanner.nextLine());
				fullList+=";";
			}
			if (scanner!=null){
				  scanner.close();
				}
			return fullList;
		}
		catch(FileNotFoundException ex){
			MagicStrings s = new MagicStrings ();
			throw new Exception(s.getFileNotfound());
		  }
		catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}

	//List van Strings met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe
	@Override
	public List<String> getReservationList() throws Throwable {
		try{
			fullList = storage.getData();
			String fullStringList = fullList.toString();
			String [] splitList = fullStringList.split(";");
			for ( String s : splitList){
				reservationList.add(s);
			}
			return reservationList;
		}
		catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}

	//Schrijvt volledige string (overschrijft hard)
	@Override
	public void setData(String data) throws Throwable {
		String [] splitList = data.split(";");
		boolean b = true;
		for (String s : splitList){
			if(!storage.dataFormatCheck(s)&& b){
				b = false;
			}
		}
		if(b){
			try{
				PrintWriter writer = new PrintWriter(file);
				for (String s : splitList){
					writer.print(s);
					writer.println();
				}
				writer.close();
			}
			catch(FileNotFoundException ex){
				throw new Exception(ms.getFileNotfound());
			  }
			catch(Exception ex){
				throw new Exception(ex.getMessage());
			}
		}
		else{
			throw new Exception(ms.getDateFormatWrong());
		}
	}

	//List van Strings met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe  => roept setdata aan
	@Override
	public void setReservationList(List<String> reservationList) throws Throwable  {
		try{
			fullList = new String();
			for( String s : reservationList){
				fullList += s;
			}
			DataStorage storage = new DataStorage();
			storage.setData(fullList);
		}
		catch(Exception ex){
			throw new Exception(ex.getMessage());	
		}
	}

	//Voegt een reservatie toe in vorm: 111,5/11/2015,4>Adams,Chloe   =>  voegt toe aan interne list stringbuffer en doet setdata
	@Override
	public void addReservation(String reservation) throws Throwable {
		
		try{
			if(storage.dataFormatCheck(reservation)){
				fullList = storage.getData();
				fullList += ";";
				fullList += reservation;
				storage.setData(fullList);
			}
			else{
				throw new Exception(ms.getDateFormatWrong());
			}
		}
		catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}

	//Controleert of ingevoerde data correct is
	//huisnummer,vandatum,aantalnachten>naam,voornaam 
	@Override
	public boolean dataFormatCheck(String data) throws Throwable {
		boolean b = true;
		int houseNumber = 0;
		int nights = 0;

		try{
		String [] splitList = data.split(">");
		String [] splitReservationNumberDateNight = splitList[0].split(",");
		String [] splitName = splitList[1].split(",");
		if(splitList.length == 2 || splitReservationNumberDateNight.length == 3 || splitName.length == 2){
			try{
					houseNumber = Integer.parseInt(splitReservationNumberDateNight[0]);
					new DateInt(splitReservationNumberDateNight[1]);
					nights = Integer.parseInt(splitReservationNumberDateNight[3]);
				}
			catch(Exception ex){
					b = false;
				}
			if(houseNumber > 999 || nights == 0 )
				{
					b = false;
				}
			}
		}
		catch(Exception ex){
			b = false;
		}
		return b;
	}
}
