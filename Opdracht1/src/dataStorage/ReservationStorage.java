package dataStorage;

import java.util.ArrayList;
import java.util.List;
import dataStorage.DataStorageInterface;
import model.Date;
import model.DateFactory;
import application.House;
import dataStorage.HouseService;
import application.Reservation;
import common.CompositeException;
import common.MagicStrings;
import application.Person;

public class ReservationStorage implements ReservationStorageInterface  {

	private DataStorageInterface storage = new DataStorage();
	private List<House> houses = new ArrayList<House>();
	private List<Reservation> reservations = new ArrayList<Reservation>();
	private Boolean refresh = false;
	
	public ReservationStorage() throws Throwable {
		this.getHouses();
	}
	
	
	
	public List<House> getHouses() throws Throwable {
		if (this.houses.size() == 0) {
			List<String> buffer = storage.getReservationList();
			for (String stringReservation : buffer) {
				try {
					int index= stringReservation.indexOf(',');
					if (index <= 0) throw new Exception("Data not in correct string format");
					String displayNumber = stringReservation.substring(0, index);
					
					House house = createHouse(displayNumber);
					if (!this.houses.contains(house)) this.houses.add(house);
				} catch (Exception e) {
					continue;
				}
			}
		}
		return this.houses;
	}

	private House createHouse(String displayNumber) throws Exception {
		int houseNumber = Integer.parseInt(displayNumber);
		House house = new House();
		house.setDisplayNumber(houseNumber);
		return house;
	}
	
	@Override
	public List<Reservation> getReservations() throws Throwable {
		if (reservations.size() == 0 || refresh) {
			List<String> buffer = storage.getReservationList();
			List<Exception> exceptionList = new ArrayList<Exception>();
			for (String stringReservation : buffer) {
				try {
					String[] mainParts = stringReservation.split("[,\\>]");
					if (mainParts.length != 5)
						throw new Exception("Reservation not in correct string format");

					Date startDate = DateFactory.generateDate(mainParts[1]);
					int numberOfDays = Integer.parseInt(mainParts[2]);
					House house = createHouse(mainParts[0]);
					if (!this.getAllHouses().contains(house)) throw new Exception(MagicStrings.houseNotFound);
					Person person = createPerson(mainParts[4], mainParts[3]);

					Reservation reservation = new Reservation();
					reservation.setStartDate(startDate);
					reservation.setHouse(house);
					reservation.setNumberOfDays(numberOfDays);
					reservation.setPerson(person);

					reservations.add(reservation);
				} catch (Exception e) {
					exceptionList.add(e);
				}
			} 
			if (!exceptionList.isEmpty()) throw new CompositeException(exceptionList);
		}
		return reservations;
	}

	private Person createPerson(String firstName, String lastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		return person;
	}

	@Override
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}

	@Override
	public void removeReservation(Reservation reservation) {
		if (reservations.contains(reservation)) {
			reservations.remove(reservation);
		}
	}

	
	private List<House> getAllHouses() throws Throwable {
		HouseService hs = new HouseService();
		return hs.getAllHouses();
	}
}
