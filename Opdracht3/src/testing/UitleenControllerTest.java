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

import common.enums.EnumTypeGame;
import controller.ControllerException;
import controller.UitleenController;
import model.Address;
import model.Customer;
import model.Item;
import model.Person;
import model.Uitlening;
import model.subItems.Game;

public class UitleenControllerTest {

	UitleenController uitleenController;
	
	Item item;
	Customer customer;
	DateTime dateNow;
	DateTime datePast;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		uitleenController = new UitleenController();
		
		item = new Game("titel", new BigDecimal(3), 3.0,EnumTypeGame.FIRSTPERSONSHOOTER);
		customer = new Customer(new Person("First","Last"),new Address("street","number","","3300","City","Country"),"bla@bla.com");
		dateNow = new DateTime(DateTime.now());
		datePast = new DateTime(1,1,1,0,0);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAanmakenVanEenUitlening() throws Exception {
		uitleenController.aanmakenVanEenUitlening(item, customer, 5, dateNow);
	}
	
	@Test(expected=ControllerException.class)
	public void testAanmakenVanEenUitleningInThePast() throws Exception {
		uitleenController.aanmakenVanEenUitlening(item, customer, 5, datePast);
	}
	
	@Test(expected=ControllerException.class)
	public void testAanmakenVanEenUitleningWithNegativeNumberOfDays() throws Exception {
		uitleenController.aanmakenVanEenUitlening(item, customer, -1, dateNow);
	}
	
	@Test(expected=ControllerException.class)
	public void testAanmakenVanEenUitleningWithAnAlreadyRentedItem() throws Exception {
		uitleenController.aanmakenVanEenUitlening(item, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(item, customer, 5, dateNow.plus(1));
	}
	
	@Test
	public void testIsHuidigItemMomenteelUitgeleendWhenTrue() throws ControllerException {
		uitleenController.aanmakenVanEenUitlening(item, customer, 5, dateNow);
		assertTrue(uitleenController.isHuidigItemMomenteelUitgeleend(item));
	}
	
	@Test
	public void testIsHuidigItemMomenteelUitgeleendWhenFalse() throws ControllerException {
		assertFalse(uitleenController.isHuidigItemMomenteelUitgeleend(item));
	}

	@Test
	public void testUitgeleendeItemsVanHuidigeKlantWhenNoneAreRented() {
		List<Item> emptyItemsList = new ArrayList<Item>();
		
		assertEquals(emptyItemsList, uitleenController.uitgeleendeItemsVanHuidigeKlant(customer));
	}
	
	@Test
	public void testUitgeleendeItemsVanHuidigeKlantWhenHeHasRented() throws ControllerException {
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(item);
		uitleenController.aanmakenVanEenUitlening(item, customer, 5, dateNow);
		
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
		itemList.add(item);
		uitleenController.aanmakenVanEenUitlening(item, customer, 5, dateNow);
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
		itemList.add(item);
		uitleenController.aanmakenVanEenUitlening(item, customer, 5, dateNow);
		assertEquals(itemList, uitleenController.alleUitgeleendeGames());
	}
	
	@Test
	public void testAlleUitgeleendeGamesWhenNoneAreRented() {
		List<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, uitleenController.alleUitgeleendeGames());
	}

	@Test
	public void testUitleningVanEenItemStoppen() throws ControllerException {
		uitleenController.aanmakenVanEenUitlening(item, customer, 5, dateNow);
		Uitlening uitlening = new Uitlening();
		uitlening.setUitgeleendItem(item);
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
