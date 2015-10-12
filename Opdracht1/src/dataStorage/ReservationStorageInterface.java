package dataStorage;

import java.util.List;

import application.Reservation;

public interface ReservationStorageInterface {
	public List<Reservation> getReservations();
	public void addReservation(Reservation reservation);
	public void removeReservation(Reservation reservation);
}
