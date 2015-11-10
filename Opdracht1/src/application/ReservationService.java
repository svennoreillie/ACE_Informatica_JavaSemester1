/**
 * 
 */
package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.MagicStrings;
import dataStorage.HouseService;
import dataStorage.ReservationStorage;
import model.Date;
import model.DateFactory;

/**
 * @author SvenNoreillie
 *
 */
public class ReservationService implements ReservationServiceInterface {

	private ReservationStorage storage = new ReservationStorage();

	@SuppressWarnings("unused")
	private Boolean refresh = false;

	public ReservationService() throws Throwable {

	}

	@Override
	public List<House> getAllHouses() throws Throwable {
		HouseService hs = new HouseService();
		return hs.getAllHouses();
	}

	@Override
	public List<House> getAvailableHouses(Date startDate, int numberOfDays) throws Throwable {
		List<House> availableHouses = getAllHouses();
		HashMap<Date, List<Reservation>> map = createReservationMap();

		for (int i = 0; i < numberOfDays; i++) {
			List<Reservation> reservations = map.get(startDate.changeDate(i));
			if (reservations != null) {
				for (Reservation reservation : reservations) {
					availableHouses.remove(reservation.getHouse());
				}
			}
		}

		return availableHouses;
	}

	@Override
	public List<Reservation> getReservationsForDate(Date date) throws Throwable {
		List<Reservation> returnReservations = new ArrayList<Reservation>();
		List<Reservation> reservations = storage.getReservations();
		for (Reservation reservation : reservations) {
			if (date.smallerThan(reservation.getStartDate()))
				continue;
			int differenceDays = date.differenceInDays(reservation.getStartDate());
			if (differenceDays < reservation.getNumberOfDays())
				returnReservations.add(reservation);
		}
		return returnReservations;
	}

	@Override
	public List<Reservation> getReservationsForHouseOnDate(House house, Date date) throws Throwable {
		List<Reservation> returnReservations = new ArrayList<Reservation>();
		List<Reservation> reservations = storage.getReservations();
		for (Reservation reservation : reservations) {
			if (date.smallerThan(reservation.getStartDate()))
				continue;
			int differenceDays = date.differenceInDays(reservation.getStartDate());
			if (differenceDays < reservation.getNumberOfDays()) {
				if (reservation.getHouse().equals(house))
					returnReservations.add(reservation);
			}
		}
		return returnReservations;
	}

	@Override
	public Date getFirstAvailableDate() throws Throwable {
		Date current = DateFactory.generateDate();

		while (getAvailableHouses(current, 1).size() > 0) {
			current.alterDate(1);
		}

		return current;
	}

	@Override
	public Date getFirstAvailableDate(House house) throws Throwable {
		if (house == null)
			throw new Exception(MagicStrings.houseNotFound);
		Date current = DateFactory.generateDate();

		while (getReservationsForHouseOnDate(house, current).size() > 0) {
			current.alterDate(1);
		}

		return current;
	}

	@Override
	public Date getFirstAvailableDate(House house, int numberOfDays) throws Throwable {
		HashMap<Date, List<Reservation>> map = createReservationMap();

		Date startDate = DateFactory.generateDate();
		int nextAlter = 0;

		do {
			startDate.alterDate(nextAlter);
			nextAlter = 0;
			for (int i = 0; i < numberOfDays; i++) {

				Boolean houseAvailable = true;
				List<Reservation> reservations = map.get(startDate.changeDate(i));
				if (reservations == null) continue;
				
				for (Reservation reservation : reservations) {
					if (reservation.getHouse() == house) {
						houseAvailable = false;
						nextAlter = (i + 1);
						break;
					}
				}

				if (!houseAvailable) {
					break;
				}

			}
		} while (nextAlter != 0);

		return startDate;
	}

	@Override
	public Reservation getFirstReservationForPerson(Person person) throws Throwable {
		List<Reservation> reservations = getReservationsForPerson(person);

		Reservation returnReservation = null;
		for (Reservation reservation : reservations) {
			if (returnReservation == null) {
				returnReservation = reservation;
			} else {
				if (reservation.getStartDate().smallerThan(returnReservation.getStartDate())) {
					returnReservation = reservation;
				}
			}
		}

		return returnReservation;
	}

	@Override
	public Reservation getFirstReservationForPerson(String firstName, String lastName) throws Throwable {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		return getFirstReservationForPerson(person);
	}

	@Override
	public List<Reservation> getReservationsForPerson(Person person) throws Throwable {
		HashMap<Person, List<Reservation>> map = createPersonMap();
		List<Reservation> reservations = map.get(person);
		if (reservations == null || reservations.size() == 0) {
			throw new Exception(String.format("No reservations found for person %s", person.toString()));
		}
		return reservations;
	}

	@Override
	public List<Reservation> getReservationsForPerson(String firstName, String lastName) throws Throwable {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		return getReservationsForPerson(person);
	}

	@Override
	public void CreateReservation(Reservation reservation) throws Throwable {
		// Check all fields given
		if (reservation == null)
			throw new Exception(MagicStrings.reservationNotFound);

		House house = reservation.getHouse();
		Date startDate = reservation.getStartDate();

		if (house == null)
			throw new Exception(MagicStrings.houseNotFound);
		if (!getAllHouses().contains(house))
			throw new Exception(MagicStrings.houseNotFound);
		if (reservation.getPerson() == null)
			throw new Exception(MagicStrings.personNotFound);
		if (startDate.smallerThan(DateFactory.generateDate()))
			throw new Exception(MagicStrings.reservationDateError);

		for (int i = 0; i < reservation.getNumberOfDays(); i++) {
			if (getReservationsForHouseOnDate(house, startDate.changeDate(i)).size() > 0) {
				throw new Exception(MagicStrings.reservationAvailabilityError);
			}
		}

		insertReservation(reservation);
	}

	// HELPERS
	private void insertReservation(Reservation reservation) {
		storage.addReservation(reservation);
		this.refresh = true;
	}

	private HashMap<Date, List<Reservation>> createReservationMap() throws Throwable {
		HashMap<Date, List<Reservation>> map = new HashMap<Date, List<Reservation>>();
		List<Reservation> reservations = storage.getReservations();
		for (Reservation reservation : reservations) {

			for (int i = 0; i < reservation.getNumberOfDays(); i++) {
				Date theDate = reservation.getStartDate().changeDate(i);
				List<Reservation> reservationList = map.get(theDate);
				if (reservationList == null) {
					reservationList = new ArrayList<Reservation>();
				}

				reservationList.add(reservation);
				map.put(theDate, reservationList);

			}

		}
		return map;
	}

	private HashMap<Person, List<Reservation>> createPersonMap() throws Throwable {
		HashMap<Person, List<Reservation>> map = new HashMap<Person, List<Reservation>>();
		List<Reservation> reservations = storage.getReservations();
		for (Reservation reservation : reservations) {

			Person person = reservation.getPerson();
			List<Reservation> reservationList = map.get(person);
			if (reservationList == null) {
				reservationList = new ArrayList<Reservation>();
			}

			reservationList.add(reservation);
			map.put(person, reservationList);

		}
		return map;
	}
}
