package model;

public interface Datum {
	public boolean setDatum(int dag, int maand, int jaar);
	
	public String getDatumInAmerikaansFormaat();
	public String getDatumInEuropeesFormaat();
	
	public boolean kleinerDan(Datum d);
	public int verschilInJaren (Datum d);
	public int verschilInMaanden (Datum d);
	public int verschilInDagen (Datum d);
	public Datum veranderDatum(int aantalDagen);
}