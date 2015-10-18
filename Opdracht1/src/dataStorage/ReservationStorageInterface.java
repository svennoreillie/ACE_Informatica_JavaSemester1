package dataStorage;

import java.util.List;

import application.House;
import application.Reservation;

public interface ReservationStorageInterface {
	public List<House> getHouses() throws Throwable;
	public List<Reservation> getReservations() throws Throwable;
	public void addReservation(Reservation reservation);
	public void removeReservation(Reservation reservation);
}
