package dataStorage;

import java.util.List;

public interface dataStorage {

	public StringBuffer getData();
	public List<StringBuffer> getReservationList();
	public void setData(StringBuffer data);
	public void setReservationList (List<StringBuffer>reservationList);
	public void dataFormatCheck (StringBuffer data);
	
}
