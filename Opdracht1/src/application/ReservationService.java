/**
 * 
 */
package application;

import java.util.List;

import model.Date;

/**
 * @author SvenNoreillie
 *
 */
public class ReservationService implements ReservationServiceInterface {

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getAllHouses()
	 */
	@Override
	public List<House> getAllHouses() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getAvailableHouses(model.Date, int)
	 */
	@Override
	public List<House> getAvailableHouses(Date startDate, int numberOfDays) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getReservationsForDate(model.Date)
	 */
	@Override
	public List<Reservation> getReservationsForDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getFirstAvailableDate()
	 */
	@Override
	public Date getFirstAvailableDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getFirstAvailableDate(application.House)
	 */
	@Override
	public Date getFirstAvailableDate(House house) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getFirstAvailableDate(int)
	 */
	@Override
	public Date getFirstAvailableDate(int numberOfDays) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getFirstAvailableDate(application.House, int)
	 */
	@Override
	public Date getFirstAvailableDate(House house, int numberOfDays) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getFirstReservationForPerson(application.Person)
	 */
	@Override
	public Reservation getFirstReservationForPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getFirstReservationForPerson(java.lang.String, java.lang.String)
	 */
	@Override
	public Reservation getFirstReservationForPerson(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getReservationsForPerson(application.Person)
	 */
	@Override
	public List<Reservation> getReservationsForPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#getReservationsForPerson(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Reservation> getReservationsForPerson(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see application.ReservationServiceInterface#CreateReservation(application.Reservation)
	 */
	@Override
	public void CreateReservation(Reservation reservation) {
		// TODO Auto-generated method stub

	}

}
