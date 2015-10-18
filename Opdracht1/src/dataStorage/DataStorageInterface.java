package dataStorage;

import java.util.List;

public interface DataStorageInterface {

	//Geeft volledige textfile terug in stringbuffer
	public String getData();
	//List van Stringbuffers met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe
	public List<String> getReservationList();
	//Schrijvt volledige stringbuffer (overschrijft hard)
	public void setData(String data);
	//List van Stringbuffers met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe  => roept setdata aan
	public void setReservationList (List<String>reservationList);
	//Voegt een reservatie toe in vorm: 111,5/11/2015,4>Adams,Chloe   =>  voegt toe aan interne list stringbuffer en doet setdata
	public void addReservation(String reservation);
	//Controleert of ingevoerde data correct is.
	public void dataFormatCheck (String data);
	
	
	
	
}
