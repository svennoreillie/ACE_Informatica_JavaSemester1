package testing;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.BoeteController;
import model.Uitlening;

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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBerekenBoeteWaarde() {
		assertEquals(0,boeteController.berekenBoeteWaarde(uitleningZonderBoete));
		assertEquals(15,boeteController.berekenBoeteWaarde(uitleningMetBoete));
	}

	@Test
	public void testIsErOpHuidigeUitleningEenBoete() {
		assertEquals(true,boeteController.isErOpHuidigeUitleningEenBoete(uitleningMetBoete));
		assertEquals(false,boeteController.isErOpHuidigeUitleningEenBoete(uitleningZonderBoete));
	}

}
