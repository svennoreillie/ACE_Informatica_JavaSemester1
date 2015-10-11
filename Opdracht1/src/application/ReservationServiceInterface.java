package application;

import java.util.List;

import model.Date;

public interface ReservationServiceInterface {

	public List<House> getAllHouses();
	public List<House> getAvailableHouses(Date startDate, int numberOfDays);
	public List<Reservation> getReservationsForDate(Date date);
	public Date getFirstAvailableDate();
	public Date getFirstAvailableDate(House house);
	public Date getFirstAvailableDate(int numberOfDays);
	public Date getFirstAvailableDate(House house, int numberOfDays);
	
	
	public Reservation getFirstReservationForPerson(Person person);
	public Reservation getFirstReservationForPerson(String firstName, String lastName);
	
	public List<Reservation> getReservationsForPerson(Person person);
	public List<Reservation> getReservationsForPerson(String firstName, String lastName);
	
	public void CreateReservation(Reservation reservation);

}
