package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
			int aantalBoeteDagen = Integer.parseInt( (Days.daysBetween(systemDate, uitleenController.geefEindDatumVanDeUitlening(uitlening))).toString());
			int boetePrijs = (uitlening.getUitgeleendItem().getBoetePrijsPerDag())* aantalBoeteDagen;
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
