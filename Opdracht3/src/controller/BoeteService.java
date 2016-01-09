package controller;

/**
 * 
 * @author Vervoort Peter
 *
 */

import java.math.BigDecimal;
import model.Uitlening;

public interface BoeteService {
			 
	public BigDecimal berekenBoeteWaarde (Uitlening uitlening);
	
	public boolean isErOpHuidigeUitleningEenBoete (Uitlening uitlening);
	
}
