package testing;

/**
 * 
 * @author Vervoort Peter
 *
 */

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import common.enums.*;
import controller.WinkelController;
import model.subItems.*;

public class WinkelControllerTest {

	private Cd cd;
	private Cd cd2;
	private Dvd dvd;
	private Dvd dvd2;
	private Game game;
	private Game game2;
	private static double d = 3;
	private static BigDecimal bDecimal = new BigDecimal("3");
	WinkelController<Cd> baseCdWinkel = new WinkelController<Cd>(Cd.class);
	WinkelController<Dvd> baseDvdWinkel = new WinkelController<Dvd>(Dvd.class);
	WinkelController<Game> baseGameWinkel = new WinkelController<Game>(Game.class);
	
	@Before
	public void setUp() throws Exception {
		cd = new Cd("titel", bDecimal, d, EnumTypeCd.GAMES);
		dvd = new Dvd("titel", bDecimal, d, EnumTypeDvd.FILM);
		game = new Game("titel", bDecimal, d, EnumTypeGame.ROLEPLAYINGGAME);
		cd2 = new Cd("atitel", bDecimal, d, EnumTypeCd.GAMES);
		dvd2 = new Dvd("atitel", bDecimal, d, EnumTypeDvd.FILM);
		game2 = new Game("atitel", bDecimal, d, EnumTypeGame.ROLEPLAYINGGAME);
	}

	@Test
	public void testAddItemToStore() {
		baseCdWinkel.AddItemToStore(cd);
		baseDvdWinkel.AddItemToStore(dvd);
		baseGameWinkel.AddItemToStore(game);
		baseCdWinkel.RemoveItemToStore(cd);
		baseDvdWinkel.RemoveItemToStore(dvd);
		baseGameWinkel.RemoveItemToStore(game);
	}

	@Test
	public void testRemoveItemToStore() {
		baseCdWinkel.AddItemToStore(cd);
		baseDvdWinkel.AddItemToStore(dvd);
		baseGameWinkel.AddItemToStore(game);
		baseCdWinkel.RemoveItemToStore(cd);
		baseDvdWinkel.RemoveItemToStore(dvd);
		baseGameWinkel.RemoveItemToStore(game);
	}

	@Test
	public void testGetAllSortedByName() {
		baseCdWinkel.AddItemToStore(cd);
		baseDvdWinkel.AddItemToStore(dvd);
		baseGameWinkel.AddItemToStore(game);
		baseCdWinkel.AddItemToStore(cd2);
		baseDvdWinkel.AddItemToStore(dvd2);
		baseGameWinkel.AddItemToStore(game2);
		assertEquals(cd,baseCdWinkel.getAllSortedByName().get(0));
		assertEquals(dvd,baseDvdWinkel.getAllSortedByName().get(0));
		assertEquals(game,baseGameWinkel.getAllSortedByName().get(0));
		baseCdWinkel.RemoveItemToStore(cd);
		baseDvdWinkel.RemoveItemToStore(dvd);
		baseGameWinkel.RemoveItemToStore(game);
		baseCdWinkel.RemoveItemToStore(cd2);
		baseDvdWinkel.RemoveItemToStore(dvd2);
		baseGameWinkel.RemoveItemToStore(game2);
	}

	@Test
	public void testSearchByStringPart() {
		baseCdWinkel.AddItemToStore(cd);
		baseDvdWinkel.AddItemToStore(dvd);
		baseGameWinkel.AddItemToStore(game);
		assertEquals(cd,baseCdWinkel.searchByStringPart("titel").get(0));
		assertEquals(dvd,baseDvdWinkel.searchByStringPart("titel").get(0));
		assertEquals(game,baseGameWinkel.searchByStringPart("titel").get(0));
		
		assertEquals(cd,baseCdWinkel.searchByStringPart("ite").get(0));
		assertEquals(dvd,baseDvdWinkel.searchByStringPart("ite").get(0));
		assertEquals(game,baseGameWinkel.searchByStringPart("ite").get(0));
		
		assertEquals(0,baseCdWinkel.searchByStringPart("zzzzzzz").size());
		assertEquals(0,baseDvdWinkel.searchByStringPart("zzzzzzz").size());
		assertEquals(0,baseGameWinkel.searchByStringPart("zzzzzzz").size());
		
		baseCdWinkel.RemoveItemToStore(cd);
		baseDvdWinkel.RemoveItemToStore(dvd);
		baseGameWinkel.RemoveItemToStore(game);
	}

}
