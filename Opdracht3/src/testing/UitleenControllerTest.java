package testing;

import static org.junit.Assert.*;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.DBException;
import common.DBMissingException;
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
	Uitlening gameUitlening;
	Uitlening cdUitlening;
	Uitlening dvdUitlening;

	@Before
	public void setUp() throws Exception {
		uitleenController = new UitleenController();

		game = new Game("game", new BigDecimal(3), 3.0, EnumTypeGame.FIRSTPERSONSHOOTER);
		cd = new Cd("cd", new BigDecimal(3), 3.0, EnumTypeCd.MUZIEK);
		dvd = new Dvd("dvd", new BigDecimal(3), 3.0, EnumTypeDvd.FILM);

		customer = new Customer(new Person("First", "Last"),
				new Address("street", "number", "", "3300", "City", "Country"), "bla@bla.com");
		dateNow = new DateTime(DateTime.now());
		datePast = new DateTime(1, 1, 1, 0, 0);
		
		gameUitlening=new Uitlening();
		gameUitlening.setBeginVerhuurDatum(dateNow);
		gameUitlening.setVerhuurPeriodeInDagen(5);
		gameUitlening.setUitgeleendItem(game);
		gameUitlening.setKlantDieUitleent(customer);
		
		cdUitlening=new Uitlening();
		cdUitlening.setBeginVerhuurDatum(dateNow);
		cdUitlening.setVerhuurPeriodeInDagen(5);
		cdUitlening.setUitgeleendItem(cd);
		cdUitlening.setKlantDieUitleent(customer);
		
		dvdUitlening=new Uitlening();
		dvdUitlening.setBeginVerhuurDatum(dateNow);
		dvdUitlening.setVerhuurPeriodeInDagen(5);
		dvdUitlening.setUitgeleendItem(dvd);
		dvdUitlening.setKlantDieUitleent(customer);
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

	@Test(expected = ControllerException.class)
	public void testAanmakenVanEenUitleningInThePast() throws Exception {
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, datePast);
	}

	@Test(expected = ControllerException.class)
	public void testAanmakenVanEenUitleningWithNegativeNumberOfDays() throws Exception {
		uitleenController.aanmakenVanEenUitlening(game, customer, -1, dateNow);
	}

	@Test(expected = ControllerException.class)
	public void testAanmakenVanEenUitleningWithAnAlreadyRentedItem() throws Exception {
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow.plus(1));
	}

	@Test
	public void testIsHuidigItemMomenteelUitgeleendWhenTrue() throws ControllerException, DBMissingException, DBException {
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		assertTrue(uitleenController.isHuidigItemMomenteelUitgeleend(game));
	}

	@Test
	public void testIsHuidigItemMomenteelUitgeleendWhenFalse() throws ControllerException, DBMissingException, DBException {
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		assertFalse(uitleenController.isHuidigItemMomenteelUitgeleend(game));
	}

	@Test
	public void testUitgeleendeItemsVanHuidigeKlantWhenNoneAreRented() throws DBMissingException, DBException {
		List<Item> emptyItemsList = new ArrayList<Item>();

		assertEquals(emptyItemsList, uitleenController.uitleningnenVanKlant(customer));
	}

	@Test
	public void testUitgeleendeItemsVanHuidigeKlantWhenHeHasRented() throws ControllerException, DBMissingException, DBException {
		List<Uitlening> itemList = new ArrayList<Uitlening>();
		itemList.add(gameUitlening);
		itemList.add(cdUitlening);
		itemList.add(dvdUitlening);

		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);

		assertEquals(itemList, uitleenController.uitleningnenVanKlant(customer));
	}

	@Test
	public void testAlleUitgeleendeItemsWhenNoneAreRented() throws DBMissingException, DBException {
		List<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, uitleenController.alleUitleningen());
	}

	@Test
	public void testAlleUitleningen() throws ControllerException, DBMissingException, DBException {
		List<Uitlening> itemList = new ArrayList<>();
		itemList.add(gameUitlening);
		itemList.add(cdUitlening);
		itemList.add(dvdUitlening);

		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);

		assertEquals(itemList, uitleenController.alleUitleningen());
	}

	@Test
	public void testAlleUitleningenVanCd() throws ControllerException, DBMissingException, DBException {
		List<Uitlening> itemList = new ArrayList<Uitlening>();
		itemList.add(cdUitlening);

		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);

		assertEquals(itemList, uitleenController.alleUitleningenVanCd());
	}

	@Test
	public void testAlleUitgeleendeDvd() throws ControllerException, DBMissingException, DBException {
		List<Uitlening> itemList = new ArrayList<Uitlening>();
		itemList.add(dvdUitlening);

		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);

		assertEquals(itemList, uitleenController.alleUitleningenVanDvd());
	}

	@Test
	public void testAlleUitleningenVanGame() throws ControllerException, DBMissingException, DBException {
		List<Uitlening> itemList = new ArrayList<Uitlening>();
		itemList.add(gameUitlening);
		
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);

		assertEquals(itemList, uitleenController.alleUitleningenVanGame());
	}

	@Test
	public void testAlleUitgeleendeGamesWhenNoneAreRented() throws DBMissingException, DBException {
		List<Item> itemList = new ArrayList<Item>();
		assertEquals(itemList, uitleenController.alleUitleningenVanGame());
	}

	@Test
	public void testUitleningVanEenItemStoppen() throws ControllerException, DBMissingException, DBException {
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);
		Uitlening uitlening = new Uitlening();
		uitlening.setUitgeleendItem(game);
		uitlening.setKlantDieUitleent(customer);
		uitlening.setBeginVerhuurDatum(dateNow);
		uitlening.setVerhuurPeriodeInDagen(5);
		uitleenController.uitleningVanEenItemStoppen(uitlening);

		ArrayList<Uitlening> itemsList = new ArrayList<Uitlening>();
		itemsList.add(cdUitlening);
		itemsList.add(dvdUitlening);

		assertEquals(itemsList, uitleenController.alleUitleningen());
	}

	@Test
	public void testUitleningVanMeerdereItemsStoppen() throws ControllerException, DBMissingException, DBException {
		uitleenController.aanmakenVanEenUitlening(game, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(cd, customer, 5, dateNow);
		uitleenController.aanmakenVanEenUitlening(dvd, customer, 5, dateNow);
			
		List<Uitlening> toDeleteItems = new ArrayList<Uitlening>();
		
		for(Uitlening u : uitleenController.getAllUitleningen()){
			toDeleteItems.add( u.Clone());
		}
		
		uitleenController.uitleningVanMeerdereItemsStoppen(toDeleteItems);
		
		assertEquals(new ArrayList<>(), uitleenController.getAllUitleningen());
	}

	@Test
	public void testGeefEindDatumVanDeUitlening() throws ControllerException {
		
		Uitlening uitlening = new Uitlening();
		uitlening.setBeginVerhuurDatum(dateNow);
		uitlening.setVerhuurPeriodeInDagen(5);
		
		DateTime actualDate = uitleenController.geefEindDatumVanDeUitlening(uitlening);
		DateTime expectedDate = dateNow.plusDays(5);
		
		assertEquals(expectedDate, actualDate);
		
	}

}
