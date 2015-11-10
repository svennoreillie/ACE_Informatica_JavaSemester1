package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import application.House;
import application.Person;
import application.Reservation;
import application.ReservationService;
import common.MagicStrings;
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
		reservation.setPerson(person);
		return reservation;
	}
	
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	
	
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
				
		rS.CreateReservation(createReservation(DateFactory.generateDate(), 7, person, reservatedHouse));
		
		List<House> availableHouses=rS.getAvailableHouses(DateFactory.generateDate(), 7);
		List<House> excpectedAvailableHouses = allHouses;
		excpectedAvailableHouses.remove(reservatedHouse);
		
		assertEquals(excpectedAvailableHouses, availableHouses);
	}

	// test getReservationsForDate met maar 1 reservatie
	@Test
	public void testGetReservationsForDateForOneReservations() throws Throwable {
		
		Date startDate = DateFactory.generateDate();
		int numberOfDays = 7;
		Person person = new Person();
		person.setFirstName("testF");
		person.setLastName("testL");
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
		
		Date startDate = DateFactory.generateDate();
		int numberOfDays = 5;
		Person person = new Person();
		House house = rS.getAllHouses().get(0);
		
		List<Reservation> expectedReservations = new ArrayList<>();
		
		Reservation reservation = createReservation(startDate, numberOfDays, person, house);
		rS.CreateReservation(reservation);
		expectedReservations.add(reservation);
		
		Reservation reservation2 = createReservation(startDate, numberOfDays, person, rS.getAllHouses().get(1));
		rS.CreateReservation(reservation2);
		expectedReservations.add(reservation2);
		
		List<Reservation> actualReservations = rS.getReservationsForDate(startDate.changeDate(1));
		
		assertEquals(expectedReservations, actualReservations);
	}

	@Test
	public void testGetReservationsForHouseOnDate() throws Exception, Throwable {
		House house = allHouses.get(0);
		Person person = new Person();
		person.setFirstName("testF");
		person.setLastName("testL");
		int duration = 10;
		
		Date date = DateFactory.generateDate();
		
		
		Reservation reservation = createReservation(date, duration, person, house);
		
		
		List<Reservation> expectedReservations = new ArrayList<>();
		expectedReservations.add(reservation);
		
		
		rS.CreateReservation(reservation);
		
		List<Reservation> reservations = rS.getReservationsForHouseOnDate(house, DateFactory.generateDate().changeDate(7));
		
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

	
	@Test
	public void testGetFirstAvailableDateHouse() throws Throwable {
		int duration = 7;
		Person person = new Person();
		person.setFirstName("testF");
		person.setLastName("testL");
		
		Date startDate = DateFactory.generateDate();
		Date expectedDate = startDate.changeDate(duration);
		
		House house = rS.getAllHouses().get(0);
		
		Reservation reservation = createReservation(startDate, duration, person, house);
		rS.CreateReservation(reservation);
		
		Date actualDate = rS.getFirstAvailableDate(house);
		
		assertEquals(expectedDate, actualDate);
	}

	@Test
	public void testGetFirstAvailableDateHouseInt() throws Throwable {
		int duration = 7;
		Person person = new Person();
		person.setFirstName("testF");
		person.setLastName("testL");
		
		Date startDate = DateFactory.generateDate();
		Date expectedDate = startDate.changeDate(duration);
		
		House house = rS.getAllHouses().get(0);
		
		Reservation reservation = createReservation(startDate, duration, person, house);
		rS.CreateReservation(reservation);
		
		Date actualDate = rS.getFirstAvailableDate(house,5);
		
		assertEquals(expectedDate, actualDate);
	}

	@Test
	public void testGetFirstReservationForPersonPerson() throws Throwable {
		int duration = 7;
		House house = rS.getAllHouses().get(0);
		
		Person person = new Person();
		person.setFirstName("Person");
		person.setLastName("1");
		
		Date date1 = DateFactory.generateDate().changeDate(1);
		Date date2 = date1.changeDate(duration+1);
		
		Reservation reservation1=createReservation(date2, duration, person, house);
		
		rS.CreateReservation(reservation1);
		
		Reservation reservation2=createReservation(date1, duration, person, house);
		rS.CreateReservation(reservation2);
		
		Reservation actualReservation = rS.getFirstReservationForPerson(person);
		
		assertEquals(reservation2, actualReservation);
	}

	@Test
	public void testGetFirstReservationForPersonStringString() throws Throwable {
		Person person = new Person();
		person.setFirstName("testF");
		person.setLastName("testL");
		
		Date date = DateFactory.generateDate();
		
		int duration = 7;
		
		House house = rS.getAllHouses().get(0);
		
		Reservation reservation =createReservation(date, duration, person, house);
		rS.CreateReservation(reservation);
		
		Reservation actualReservation = rS.getFirstReservationForPerson(person.getFirstName(), person.getLastName());
		
		assertEquals(reservation, actualReservation);
	}

	@Test
	public void testGetReservationsForPersonPerson() throws Throwable {
		Person person = new Person();
		person.setFirstName("testF");
		person.setLastName("testL");
		
		
		Date date = DateFactory.generateDate();
		
		int duration = 7;
		
		House house = rS.getAllHouses().get(0);
		
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		Reservation reservation1 =createReservation(date, duration, person, house);
		rS.CreateReservation(reservation1);
		Reservation reservation2 =createReservation(date.changeDate(8), duration, person, house);
		rS.CreateReservation(reservation2);
		
		
		List<Reservation> actualReservations = rS.getReservationsForPerson(person);
		
		assertEquals(2, actualReservations.size());
	}

	@Test
	public void testGetReservationsForPersonStringString() throws Throwable {
		Person person = new Person();
		person.setFirstName("testF");
		person.setLastName("testL");
		
		
		Date date = DateFactory.generateDate(4,11,2015);
		
		int duration = 7;
		
		House house = rS.getAllHouses().get(0);
		
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		Reservation reservation1 =createReservation(date, duration, person, house);
		reservations.add(reservation1);
		Reservation reservation2 =createReservation(date.changeDate(8), duration, person, house);
		reservations.add(reservation2);
		
		
		List<Reservation> actualReservations = rS.getReservationsForPerson(person.getFirstName(),person.getLastName());
		
		assertEquals(reservations, actualReservations);
	}

	

}
