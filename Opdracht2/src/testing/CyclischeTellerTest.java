package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.tellers.CyclischeTeller;

public class CyclischeTellerTest {

	private static CyclischeTeller huidigeTeller;
	
	@Before
	public void setUp() throws Exception {
		huidigeTeller = new CyclischeTeller ('0','1','2','3');
	}

	@Test
	public void testUpdateHuidigeWaarde() {
		assertEquals('0',huidigeTeller.getHuidigeWaarde());
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('1',huidigeTeller.getHuidigeWaarde());
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('2',huidigeTeller.getHuidigeWaarde());
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('3',huidigeTeller.getHuidigeWaarde());
		huidigeTeller.updateHuidigeWaarde();
		assertEquals('0',huidigeTeller.getHuidigeWaarde());
	}
	
	@Test
	public void testResethuidigeWaarde(){
		huidigeTeller.updateHuidigeWaarde();
		huidigeTeller.resetHuidigeWaarde();
		assertEquals('0',huidigeTeller.getHuidigeWaarde());
		huidigeTeller.resetHuidigeWaarde();
		assertEquals('0',huidigeTeller.getHuidigeWaarde());
	}
}
