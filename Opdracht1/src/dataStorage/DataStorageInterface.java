package dataStorage;

import java.util.List;

public interface DataStorageInterface {

	//Geeft volledige textfile terug in stringbuffer
	public StringBuffer getData();
	//List van Stringbuffers met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe
	public List<StringBuffer> getReservationList();
	//Schrijvt volledige stringbuffer (overschrijft hard)
	public void setData(StringBuffer data);
	//List van Stringbuffers met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe  => roept setdata aan
	public void setReservationList (List<StringBuffer>reservationList);
	//Voegt een reservatie toe in vorm: 111,5/11/2015,4>Adams,Chloe   =>  voegt toe aan interne list stringbuffer en doet setdata
	public void addReservation(StringBuffer reservation);
	//Controleert of ingevoerde data correct is.
	public void dataFormatCheck (StringBuffer data);
	
	
	
	
}
