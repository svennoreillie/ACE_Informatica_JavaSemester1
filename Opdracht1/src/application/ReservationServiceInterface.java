package application;

import java.util.List;

import model.Date;

public interface ReservationServiceInterface {

	public List<House> getAllHouses() throws Throwable;
	public List<House> getAvailableHouses(Date startDate, int numberOfDays) throws Throwable;
	public List<Reservation> getReservationsForDate(Date date) throws Throwable;
	
	public List<Reservation> getReservationsForHouseOnDate(House house, Date date) throws Throwable;
	
	public Date getFirstAvailableDate() throws Throwable;
	public Date getFirstAvailableDate(House house) throws Throwable;
	public Date getFirstAvailableDate(House house, int numberOfDays) throws Throwable;
	
	
	public Reservation getFirstReservationForPerson(Person person);
	public Reservation getFirstReservationForPerson(String firstName, String lastName);
	
	public List<Reservation> getReservationsForPerson(Person person);
	public List<Reservation> getReservationsForPerson(String firstName, String lastName);
	
	public void CreateReservation(Reservation reservation);

}
