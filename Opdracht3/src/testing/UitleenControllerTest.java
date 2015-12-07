package testing;

import static org.junit.Assert.*;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.enums.EnumTypeCd;
import common.enums.EnumTypeDvd;
import common.enums.EnumTypeGame;
import controller.ControllerException;
import controller.UitleenController;
import model.Address;
import model.Customer;
import model.Item;
import model.Person;
import model.Uitlening;
import model.subItems.Cd;
import model.subItems.Dvd;
import model.subItems.Game;

/**
 * 
 * @author Huybrechts Pieter
 *
 */

public class UitleenControllerTest {

	UitleenController uitleenController;
	
	Game game;
	Cd cd;
	Dvd dvd;
	Customer customer;
	DateTime dateNow;
	DateTime datePast;
	
	@Before
	public void setUp() throws Exception {
		uitleenController = new UitleenController();
		
		game = new Game("game", new BigDecimal(3), 3.0,EnumTypeGame.FIRSTPERSONSHOOTER);
		cd = new Cd("cd", new BigDecimal(3), 3.0,EnumTypeCd.MUZIEK);
		dvd = new Dvd("dvd", new BigDecimal(3), 3.0,EnumTypeDvd.FILM);
		
		customer = new Customer(new Person("First","Last"),new Address("street","number","","3300","City","Country"),"bla@bla.com");
		dateNow = new DateTime(DateTime.now());
		datePast = new DateTime(1,1,1,0,0);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAanmakenVanEenUitlening() throws Exception {
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);
	}
	
	@Test(expected=ControllerException.class)
	public void testAanmakenVanEenUitleningInThePast() throws Exception {
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, datePast);
	}
	
	@Test(expected=ControllerException.class)
	public void testAanmakenVanEenUitleningWithNegativeNumberOfDays() throws Exception {
		uitleenController.aanmakenVanEenUitlening(game, customer, -1, dateNow);
	}
	
	@Test(expected=ControllerException.class)
	public void testAanmakenVanEenUitleningWithAnAlreadyRentedItem() throws Exception {
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow.plus(1));
	}
	
	@Test
	public void testIsHuidigItemMomenteelUitgeleendWhenTrue() throws ControllerException {
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		assertTrue(uitleenController.isHuidigItemMomenteelUitgeleend(game));
	}
	
	@Test
	public void testIsHuidigItemMomenteelUitgeleendWhenFalse() throws ControllerException {
		assertFalse(uitleenController.isHuidigItemMomenteelUitgeleend(game));
	}

	@Test
	public void testUitgeleendeItemsVanHuidigeKlantWhenNoneAreRented() {
		List<Item> emptyItemsList = new ArrayList<Item>();
		
		assertEquals(emptyItemsList, uitleenController.uitgeleendeItemsVanHuidigeKlant(customer));
	}
	
	@Test
	public void testUitgeleendeItemsVanHuidigeKlantWhenHeHasRented() throws ControllerException {
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(game);
		itemList.add(cd);
		itemList.add(dvd);
		
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		
		assertEquals(itemList, uitleenController.uitgeleendeItemsVanHuidigeKlant(customer));
	}

	@Test
	public void testAlleUitgeleendeItemsWhenNoneAreRented() {
		List<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, uitleenController.alleUitgeleendeItems());
	}
	
	@Test
	public void testAlleUitgeleendeItems() throws ControllerException {
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(game);
		itemList.add(cd);
		itemList.add(dvd);
		
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);
		
		assertEquals(itemList, uitleenController.alleUitgeleendeItems());
	}

	@Test
	public void testAlleUitgeleendeCd() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlleUitgeleendeDvd() {
		fail("Not yet implemented");
	}

	@Test
	public void testAlleUitgeleendeGames() throws ControllerException {
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(game);
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);
		
		assertEquals(itemList, uitleenController.alleUitgeleendeGames());
	}
	
	@Test
	public void testAlleUitgeleendeGamesWhenNoneAreRented() {
		List<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, uitleenController.alleUitgeleendeGames());
	}

	@Test
	public void testUitleningVanEenItemStoppen() throws ControllerException {
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		Uitlening uitlening = new Uitlening();
		uitlening.setUitgeleendItem(game);
		uitlening.setKlantDieUitleent(customer);
		uitlening.setBeginVerhuurDatum(dateNow);
		uitlening.setVerhuurPeriodeInDagen(5);
		uitleenController.uitleningVanEenItemStoppen(uitlening);
		
		assertEquals(new ArrayList<Item>(), uitleenController.alleUitgeleendeItems());
	}

	@Test
	public void testUitleningVanMeerdereItemsStoppen() throws ControllerException {
		fail("Not yet implemented");
	}

	@Test
	public void testGeefEindDatumVanDeUitlening() {
		fail("Not yet implemented");
	}

}
