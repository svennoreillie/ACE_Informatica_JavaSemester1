/**
 * 
 */
package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataStorage.ReservationStorage;
import model.Date;
import model.DateFactory;

/**
 * @author SvenNoreillie
 *
 */
public class ReservationService implements ReservationServiceInterface {

	private ReservationStorage storage = new ReservationStorage();
	private List<House> everyHouse = new ArrayList<House>();
	
	public ReservationService() throws Throwable {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.ReservationServiceInterface#getAllHouses()
	 */
	@Override
	public List<House> getAllHouses() throws Throwable {
		
		if (everyHouse != null && everyHouse.size() ==0) {
			for (int i = 1; i <= 107; i++) {
				House house = new House(i);
				everyHouse.add(house);
			}
		}
		return everyHouse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * application.ReservationServiceInterface#getAvailableHouses(model.Date,
	 * int)
	 */
	@Override
	public List<House> getAvailableHouses(Date startDate, int numberOfDays) throws Throwable {
		List<House> availableHouses = getAllHouses();
		HashMap<Date, List<Reservation>> map = createReservationMap();
		
		for (int i = 0; i < numberOfDays; i++) {
			List<Reservation> reservations = map.get(startDate.changeDate(i));
			for (Reservation reservation : reservations) {
				availableHouses.remove(reservation.getHouse());
			}
		}
		
		return availableHouses;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * application.ReservationServiceInterface#getReservationsForDate(model.
	 * Date)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.ReservationServiceInterface#getFirstAvailableDate()
	 */
	@Override
	public Date getFirstAvailableDate() throws Throwable {
		Date current = DateFactory.generateDate();

		while (getAvailableHouses(current, 1).size() > 0) {
			current.alterDate(1);
		}

		return current;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * application.ReservationServiceInterface#getFirstAvailableDate(application
	 * .House)
	 */
	@Override
	public Date getFirstAvailableDate(House house) throws Throwable {
		Date current = DateFactory.generateDate();

		while (getReservationsForHouseOnDate(house, current).size() > 0) {
			current.alterDate(1);
		}

		return current;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * application.ReservationServiceInterface#getFirstAvailableDate(application
	 * .House, int)
	 */
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

				for (Reservation reservation : reservations) {
					if (reservation.getHouse() == house) {
						houseAvailable = false;
						nextAlter = i;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * application.ReservationServiceInterface#getFirstReservationForPerson(
	 * application.Person)
	 */
	@Override
	public Reservation getFirstReservationForPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * application.ReservationServiceInterface#getFirstReservationForPerson(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public Reservation getFirstReservationForPerson(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.ReservationServiceInterface#getReservationsForPerson(
	 * application.Person)
	 */
	@Override
	public List<Reservation> getReservationsForPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * application.ReservationServiceInterface#getReservationsForPerson(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public List<Reservation> getReservationsForPerson(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * application.ReservationServiceInterface#CreateReservation(application.
	 * Reservation)
	 */
	@Override
	public void CreateReservation(Reservation reservation) {
		// TODO Auto-generated method stub

	}

}
