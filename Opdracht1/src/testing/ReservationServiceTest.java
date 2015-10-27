package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import application.House;
import application.ReservationService;

public class ReservationServiceTest {

	@Test
	public void testReservationService() throws Throwable {
		
		ReservationService rS;
		rS = new ReservationService();
		assertNotEquals(null, rS);
	
	}

	@Test
	public void testGetAllHouses() throws Throwable {
		ReservationService rS = null;
		rS=new ReservationService();
		List<House> houseList = new ArrayList<>();
		houseList = rS.getAllHouses();
		assertEquals(107,houseList.size());
	}

	/*@Test
	public void testGetAvailableHouses() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReservationsForDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReservationsForHouseOnDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFirstAvailableDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFirstAvailableDateHouse() {
		fail("Not yet implemented");
	}

	@Test
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

	@Test
	public void testCreateReservation() {
		fail("Not yet implemented");
	}*/

}
