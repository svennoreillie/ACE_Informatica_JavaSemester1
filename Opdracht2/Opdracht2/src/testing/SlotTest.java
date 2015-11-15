package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Slot;
import domain.tellers.CyclischeTeller;
import domain.tellers.OmgekeerdCyclistischeTeller;
import domain.tellers.Teller;
import domain.tellers.TerugLoopTeller;

public class SlotTest {

	private Slot testSlot;
	private List<Teller> tellerLijst;
	private Teller teller1;
	private Teller teller2;
	private Teller teller3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		tellerLijst = new ArrayList<Teller>();
		teller1 = new CyclischeTeller('1','2','3','4','5');
		tellerLijst.add(teller1);
		teller2 = new CyclischeTeller('A','B','C','D','E');
		tellerLijst.add(teller2);
		teller3 = new OmgekeerdCyclistischeTeller('1','3','5','7','9');
		tellerLijst.add(teller3);
		
		testSlot = new Slot(tellerLijst, "2C5");
	}

	@Test
	public void testGetWaardeTellerFirstIndex() {
		teller1.setHuidigeIndex(0);
		teller2.setHuidigeIndex(0);
		teller3.setHuidigeIndex(0);
		
		assertEquals('1', testSlot.getWaardeTeller(0));
		assertEquals('A', testSlot.getWaardeTeller(1));
		assertEquals('1', testSlot.getWaardeTeller(2));
	}
	
	@Test
	public void testGetWaardeTellerRandomIndex() {
		teller1.setHuidigeIndex(2);
		teller2.setHuidigeIndex(3);
		teller3.setHuidigeIndex(1);
		
		assertEquals('3', testSlot.getWaardeTeller(0));
		assertEquals('D', testSlot.getWaardeTeller(1));
		assertEquals('3', testSlot.getWaardeTeller(2));
	}

	@Test
	public void testGetTellers() {
		assertEquals(tellerLijst, testSlot.getTellers());
	}

	@Test
	public void testGetSlotCombinatie() {
		assertEquals("1A1", testSlot.getSlotCombinatie());
	}
	
	@Test
	public void testGetSlotCombinatieAfterUpdate() {
		teller1.setHuidigeIndex(2);
		teller2.setHuidigeIndex(3);
		teller3.setHuidigeIndex(1);
		
		assertEquals("3D3", testSlot.getSlotCombinatie());
	}

	@Test
	public void testUpdateHuidigeWaardeTellerInt() {
		assertEquals("1A1", testSlot.getSlotCombinatie());
		testSlot.updateHuidigeWaardeTeller(0);
		assertEquals("2A1", testSlot.getSlotCombinatie());
		testSlot.updateHuidigeWaardeTeller(0);
		assertEquals("3A1", testSlot.getSlotCombinatie());
		testSlot.updateHuidigeWaardeTeller(2);
		assertEquals("3A9", testSlot.getSlotCombinatie());
		testSlot.updateHuidigeWaardeTeller(1);
		assertEquals("3B9", testSlot.getSlotCombinatie());
	}

	@Test
	public void testGetAantalTellers() {
		assertEquals(3, testSlot.getAantalTellers());
	}

	@Test
	public void testIsGeheimGevonden() {
		assertFalse(testSlot.isGeheimGevonden());
		teller1.setHuidigeIndex(1);
		teller2.setHuidigeIndex(2);
		teller3.setHuidigeIndex(2);
		assertTrue(testSlot.isGeheimGevonden());
	}

	@Test
	public void testResetTellers() {
		teller1.setHuidigeIndex(3);
		teller2.setHuidigeIndex(2);
		teller3.setHuidigeIndex(4);
		testSlot.resetTellers();
		assertEquals("1A1", testSlot.getSlotCombinatie());
	}

}
