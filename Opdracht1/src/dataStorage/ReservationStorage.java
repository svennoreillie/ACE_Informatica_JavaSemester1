package dataStorage;

import java.util.ArrayList;
import java.util.List;
import dataStorage.DataStorageInterface;
import model.Date;
import model.DateFactory;
import application.House;
import application.Reservation;
import application.Person;

public class ReservationStorage implements ReservationStorageInterface  {

	private DataStorageInterface storage = new DataStorage();
	private List<House> houses = new ArrayList<House>();
	private List<Reservation> reservations = new ArrayList<Reservation>();
	private Boolean refresh = false;
	
	public ReservationStorage() throws Throwable {
		this.getHouses();
	}
	
	private List<House> getHouses() throws Throwable {
		if (this.houses.size() == 0) {
			List<String> buffer = storage.getReservationList();
			for (String stringReservation : buffer) {
				int index= stringReservation.indexOf(',');
				if (index <= 0) throw new Exception("Data not in correct string format");
				String displayNumber = stringReservation.substring(0, index);
				
				House house = createHouse(displayNumber);
				if (!this.houses.contains(house)) this.houses.add(house);
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
			for (String stringReservation : buffer) {
				String[] mainParts = stringReservation.split("[,\\>]");
				if (mainParts.length != 5)
					throw new Exception("Reservation not in correct string format");

				Date startDate = DateFactory.generateDate(mainParts[1]);
				int numberOfDays = Integer.parseInt(mainParts[2]);
				House house = createHouse(mainParts[0]);
				Person person = createPerson(mainParts[4], mainParts[3]);

				Reservation reservation = new Reservation();
				reservation.setStartDate(startDate);
				reservation.setHouse(house);
				reservation.setNumberOfDays(numberOfDays);
				reservation.setPerson(person);

				reservations.add(reservation);
			} 
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

}
