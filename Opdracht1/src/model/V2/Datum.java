package model.V2;

import model.DatumBase;

public class Datum extends DatumBase {

	//Dit is de datum klasse die werkt op basis van GregorianCalendar 
	//http://docs.oracle.com/javase/8/docs/api/index.html
	
	@Override
	public boolean setDatum(int dag, int maand, int jaar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDatumInAmerikaansFormaat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDatumInEuropeesFormaat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean kleinerDan(model.Datum d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int verschilInJaren(model.Datum d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int verschilInMaanden(model.Datum d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int verschilInDagen(model.Datum d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public model.Datum veranderDatum(int aantalDagen) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(model.Datum otherDate) {
		// TODO Auto-generated method stub
		return 0;
	}

}
