package dataStorage;

import java.util.List;

import application.Reservation;

public interface ReservationStorageInterface {
	public List<Reservation> getReservations() throws Exception;
	public void addReservation(Reservation reservation);
	public void removeReservation(Reservation reservation);
}
