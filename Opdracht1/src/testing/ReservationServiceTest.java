package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import application.House;
import application.Person;
import application.Reservation;
import application.ReservationService;
import model.Date;
import model.DateFactory;

public class ReservationServiceTest {
	
	ReservationService rS;
	List<House> allHouses;
	
	@Before
	public void setUp() throws Throwable{
		rS=new ReservationService();
		allHouses = rS.getAllHouses();
	}
	
	public Reservation createReservation(Date startDate,int numberOfDays,Person person, House house) throws Throwable{
		Reservation reservation = new Reservation();
		reservation.setStartDate(startDate);
		reservation.setNumberOfDays(numberOfDays);
		reservation.setHouse(house);
		return reservation;
	}
	
	//Test Constructor
	@Test
	public void testReservationService() throws Throwable {
		assertNotEquals(null, new ReservationService());
	}

	//Test getAllHouses (Vergelijkt de lengte van de lijst)
	@Test
	public void testGetAllHouses() throws Throwable {
		List<House> houseList = new ArrayList<>();
		houseList = rS.getAllHouses();
		assertEquals(107,houseList.size());
	}
	
	//Test getAvailableHouses wanneer ze allemaal beschikbaar zijn.
	@Test
	public void testGetAvailableHousesWhenAllAreAvailable() throws Throwable {
		Date startDate = DateFactory.generateDate(27,10,2015);
		
		List<House> availableHouses =  rS.getAvailableHouses(startDate, 20);
				
		assertEquals(allHouses, availableHouses);
	}
	
	//Maakt een reservatie en checkt of availableHouses gelijk is aan de lijst van alle huizen zonder het geresrveerde huis.
	@Test
	public void testCreateReservation() throws Throwable {
		
		Person person = new Person();
		person.setFirstName("test");
		person.setLastName("test");
		
		House reservatedHouse =  allHouses.get(0);
				
		rS.CreateReservation(createReservation(DateFactory.generateDate(27,10,2015), 7, person, reservatedHouse));
		
		List<House> availableHouses=rS.getAvailableHouses(DateFactory.generateDate(27,10,2015), 7);
		List<House> excpectedAvailableHouses = allHouses;
		excpectedAvailableHouses.remove(reservatedHouse);
		
		assertEquals(excpectedAvailableHouses, availableHouses);
	}

	// test getReservationsForDate met maar 1 reservatie
	@Test
	public void testGetReservationsForDateForOneReservations() throws Throwable {
		
		Date startDate = DateFactory.generateDate(27, 10, 2015);
		int numberOfDays = 7;
		Person person = new Person();
		person.setFirstName("test");
		person.setLastName("test");
		House house =  allHouses.get(0);
		
		Reservation reservation = createReservation(startDate, numberOfDays, person, house);
		
		rS.CreateReservation(reservation);
		
		List<Reservation> actualReservations=rS.getReservationsForDate(startDate);
		List<Reservation> expectedReservation = new ArrayList<Reservation>();
		expectedReservation.add(reservation);
		
		assertEquals(expectedReservation, actualReservations);
	}
	
	// test getReservationsForDate met meerdere reservaties
	@Test
	public void testGetReservationsForDateForMultipleReservations() throws Throwable {
		
		Date startDate1 = DateFactory.generateDate(27, 10, 2015);
		Date startDate2 = DateFactory.generateDate(22,10,2015);
		Date startDate3 = DateFactory.generateDate(29,10,2015);
		
		List<Reservation> expectedReservation = new ArrayList<Reservation>();
		
		int numberOfDays = 10;
		
		for(int i=0;i<12;i++){
			Person person = new Person();
			person.setFirstName("test"+i);
			person.setLastName("test"+i);
			
			Reservation reservation=createReservation(startDate1, numberOfDays, person, allHouses.get(i));
			
			switch (i%3) {
			case 0:
				reservation = createReservation(startDate1, numberOfDays, person, allHouses.get(i));
				break;
				
			case 1:
				reservation = createReservation(startDate2, numberOfDays, person, allHouses.get(i));
				break;
				
			case 2:
				reservation = createReservation(startDate3, numberOfDays, person, allHouses.get(i));
				break;

			default:
				
				break;
			}
			rS.CreateReservation(reservation);
			expectedReservation.add(reservation);
			
		}
		
		List<Reservation> actualReservations=rS.getReservationsForDate(DateFactory.generateDate(30,10,2015));

		assertEquals(expectedReservation, actualReservations);
	}

	@Test
	public void testGetReservationsForHouseOnDate() throws Exception, Throwable {
		House house = allHouses.get(0);
		Person person = new Person();
		person.setFirstName("test");
		person.setLastName("test");
		
		
		Date date = DateFactory.generateDate(30, 10, 2015);
		Date date2 = DateFactory.generateDate(2, 11, 2015);
		Date date3 = DateFactory.generateDate(3, 11, 2015);
		
		Reservation reservation = createReservation(date, 7, person, house);
		Reservation reservation2 = createReservation(date2, 7, person, house);
		
		List<Reservation> expectedReservations = new ArrayList<>();
		expectedReservations.add(reservation);
		expectedReservations.add(reservation2);
		
		
		rS.CreateReservation(reservation);
		
		List<Reservation> reservations = rS.getReservationsForHouseOnDate(house, date3);
		
		assertEquals(expectedReservations, reservations);
		
		
		
	}

	@Test
	public void testGetFirstAvailableDate() throws Throwable {
		int duration = 100;
		Person person = new Person();
		person.setFirstName("test");
		person.setLastName("test");
		Date startDate = DateFactory.generateDate(30, 10, 2015);
		Date expectedDate = startDate.changeDate(duration);
		
		for(int i = 0 ; i<107 ; i++){
			Reservation reservation = createReservation(startDate, duration, person, rS.getAllHouses().get(i));
			rS.CreateReservation(reservation);
		}
		
		Date actualDate = rS.getFirstAvailableDate();
		
		assertEquals(expectedDate,actualDate);
	}

	
	/*@Test
	public void testGetFirstAvailableDateHouse() {
		fail("Not yet implemented");
	}

	/*@Test
	public void testGetFirstAvailableDateHouseInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFirstReservationForPersonPerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFirstReservationForPersonStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReservationsForPersonPerson() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReservationsForPersonStringString() {
		fail("Not yet implemented");
	}

	*/

}
