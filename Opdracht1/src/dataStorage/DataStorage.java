package dataStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.MagicStrings;
import model.DateFactory;
import model.V1.DateInt;


public class DataStorage implements DataStorageInterface {
	
	
	private String fullList = new String();
	private List<String> reservationList = new ArrayList<String>();
	private File file = new File("reservaties.txt");
	private MagicStrings ms = new MagicStrings ();
	
	@Override
	public String getData() throws Throwable {
		try{
			Path path = Paths.get("reservaties.txt");
			Scanner scanner = new Scanner(path);
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

	//List van Strings met elke reservatie in vorm: 111,05/11/2015,4>Adams,Chloe
	@Override
	public List<String> getReservationList() throws Throwable {
		try{
			fullList = this.getData();
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

		for (String s : splitList){
			if(!this.dataFormatCheck(s)){
				throw new Exception(ms.getDateFormatWrong());
			}
		}

			try{
				PrintWriter writer = new PrintWriter(file);
				for (String s : splitList){
					writer.println(s);
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

	//List van Strings met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe  => roept setdata aan
	@Override
	public void setReservationList(List<String> reservationList) throws Throwable  {
		try{
			fullList = new String();
			for( String s : reservationList){
				fullList += s;
				fullList += ";";
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
			if(this.dataFormatCheck(reservation)){
				fullList = this.getData();
				fullList += ";";
				fullList += reservation;
				this.setData(fullList);
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
	//111,05/11/2015,4>Adams,Chloe
	@Override
	public boolean dataFormatCheck(String data) throws Throwable {
		int houseNumber = 0;
		int nights = 0;

		try{
		String [] splitList = data.split(">");
		String [] splitReservationNumberDateNight = splitList[0].split(",");
		String [] splitName = splitList[1].split(",");
		if(splitList.length == 2 && splitReservationNumberDateNight.length == 3 && splitName.length == 2){
			try{
					houseNumber = Integer.parseInt(splitReservationNumberDateNight[0]);
					DateFactory.generateDate(splitReservationNumberDateNight[1]);
					nights = Integer.parseInt(splitReservationNumberDateNight[2]);
				}
			catch(Exception ex){
					return false;
				}
			if(houseNumber > 9999 || nights <= 0 )
				{
					return false;
				}
			}
		}
		catch(Exception ex){
			return false;
		}
		return true;
	}
}
