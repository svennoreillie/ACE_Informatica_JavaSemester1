package testing;

/**
 * 
 * @author Vervoort Peter
 *
 */

import static org.junit.Assert.*;
import java.math.BigDecimal;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import common.enums.EnumTypeGame;
import controller.BoeteController;
import model.Uitlening;
import model.subItems.Game;

public class BoeteControllerTest {

	private static Uitlening uitleningMetBoete;
	private static Uitlening uitleningZonderBoete;
	private BoeteController boeteController = new BoeteController();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		uitleningZonderBoete = new Uitlening();
		uitleningZonderBoete.setBeginVerhuurDatum(new DateTime());
		uitleningZonderBoete.setVerhuurPeriodeInDagen(3);
		uitleningMetBoete = new Uitlening();
		uitleningMetBoete.setBeginVerhuurDatum(new DateTime().minusDays(10));
		uitleningMetBoete.setVerhuurPeriodeInDagen(5);
		uitleningMetBoete.setUitgeleendItem(
				new Game("Rocket League", new BigDecimal(3), 3.0, EnumTypeGame.ROLEPLAYINGGAME));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBerekenBoeteWaarde() {
		BigDecimal zero = new BigDecimal(0);
		BigDecimal vijftien = new BigDecimal(15);
		assertEquals(zero,boeteController.berekenBoeteWaarde(uitleningZonderBoete));
		assertEquals(vijftien,boeteController.berekenBoeteWaarde(uitleningMetBoete));
	}

	@Test
	public void testIsErOpHuidigeUitleningEenBoete() {
		assertEquals(true,boeteController.isErOpHuidigeUitleningEenBoete(uitleningMetBoete));
		assertEquals(false,boeteController.isErOpHuidigeUitleningEenBoete(uitleningZonderBoete));
	}

}
