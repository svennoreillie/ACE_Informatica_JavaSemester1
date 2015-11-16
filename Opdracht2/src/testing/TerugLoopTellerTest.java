package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.tellers.TerugLoopTeller;

public class TerugLoopTellerTest {
	
	private TerugLoopTeller huidigeTeller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		huidigeTeller = new TerugLoopTeller('1','2','3');
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdateHuidigeWaarde() {
		//fail("Not yet implemented");
		assertEquals('1', huidigeTeller.getHuidigeWaarde());
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('2', huidigeTeller.getHuidigeWaarde());
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('3', huidigeTeller.getHuidigeWaarde());
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('2', huidigeTeller.getHuidigeWaarde());
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('1', huidigeTeller.getHuidigeWaarde());
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('2', huidigeTeller.getHuidigeWaarde());
	}

	@Test
	public void testToString() {
		assertEquals("waarde = 1", huidigeTeller.toString());
	}
	
	@Test
	public void testResetHuidigeWaarde(){
		huidigeTeller.resetHuidigeWaarde();
		assertEquals('1', huidigeTeller.getHuidigeWaarde());
		
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('2', huidigeTeller.getHuidigeWaarde());
		
		huidigeTeller.resetHuidigeWaarde();
		assertEquals('1', huidigeTeller.getHuidigeWaarde());
	}
}
