package dataStorage;

import java.util.List;

public interface DataStorageInterface {

	//Geeft volledige textfile terug in string, elke reservatie is gescheiden door ";"
	public String getData() throws Throwable;
	//List van String met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe
	public List<String> getReservationList() throws Throwable;
	//Schrijvt volledige string (overschrijft hard)
	public void setData(String data) throws Throwable;
	//List van String met elke reservatie in vorm: 111,5/11/2015,4>Adams,Chloe  => roept setdata aan
	public void setReservationList (List<String>reservationList) throws Exception, Throwable;
	//Voegt een reservatie toe in vorm: 111,5/11/2015,4>Adams,Chloe   =>  voegt toe aan interne list stringbuffer en doet setdata
	public void addReservation(String reservation) throws Throwable;
	//Controleert of ingevoerde data correct is.
	public boolean dataFormatCheck (String data) throws Throwable;
	
}
