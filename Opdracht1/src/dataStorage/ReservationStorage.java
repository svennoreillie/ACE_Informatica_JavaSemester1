package dataStorage;

import java.util.List;
import dataStorage.DataStorageInterface;

import application.Reservation;

public class ReservationStorage implements ReservationStorageInterface  {

	private DataStorageInterface storage;
	
	@Override
	public List<Reservation> getReservations() {
		
		//return storage.getReservationList();
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addReservation(Reservation reservation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeReservation(Reservation reservation) {
		// TODO Auto-generated method stub

	}

}
