package controller;

import java.math.BigDecimal;
import model.Uitlening;

public interface BoeteService {
		
	public class Boetes {

		public Boetes() {
			// TODO Auto-generated constructor stub
		}

	}

	public class Boete {

		public Boete() {
			// TODO Auto-generated constructor stub
		}

	}

	//TODO exceptions 
	public BigDecimal berekenBoeteWaarde (Uitlening uitlening);
	
	public boolean isErOpHuidigeUitleningEenBoete (Uitlening uitlening);
	
}
