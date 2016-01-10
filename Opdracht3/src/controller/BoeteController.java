package controller;

/**
 * 
 * @author Vervoort Peter
 *
 */

import java.math.BigDecimal;
import org.joda.time.DateTime;
import org.joda.time.Days;

import model.Uitlening;

public class BoeteController implements BoeteService {

	@Override
	public BigDecimal berekenBoeteWaarde(Uitlening uitlening) {
		BigDecimal boeteBedrag = new BigDecimal (0);
		if(isErOpHuidigeUitleningEenBoete(uitlening)){
			DateTime systemDate = new DateTime();
			UitleenController uitleenController = new UitleenController();
			Days daysBetweenString = Days.daysBetween(uitleenController.geefEindDatumVanDeUitlening(uitlening), systemDate);
			int aantalBoeteDagen = daysBetweenString.getDays();
			int absAantalBoeteDagen = Math.abs(aantalBoeteDagen);
			int boetePrijs = (uitlening.getUitgeleendItem().getBoetePrijsPerDag())* absAantalBoeteDagen;
			boeteBedrag = new BigDecimal (boetePrijs);
			return boeteBedrag;
		}	
		return boeteBedrag;
	}


	@Override
	public boolean isErOpHuidigeUitleningEenBoete(Uitlening uitlening) {

		DateTime systemDate = new DateTime();
		UitleenController uitleenController = new UitleenController();
		if(systemDate.compareTo(uitleenController.geefEindDatumVanDeUitlening(uitlening))>0){
			return true;
		}
		return false;
	}
}
