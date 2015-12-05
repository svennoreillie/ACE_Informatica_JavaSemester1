package controller;

import java.math.BigDecimal;
import model.Uitlening;

public interface BoeteService {
		
	//TODO exceptions 
	public BigDecimal berekenBoeteWaarde (Uitlening uitlening);
	
	public boolean isErOpHuidigeUitleningEenBoete (Uitlening uitlening);
	
}
