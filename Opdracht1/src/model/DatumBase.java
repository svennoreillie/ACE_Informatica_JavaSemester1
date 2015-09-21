package model;

public abstract class DatumBase implements Datum {
	
	@Override
	public abstract boolean setDatum(int dag, int maand, int jaar);

	@Override
	public abstract String getDatumInAmerikaansFormaat();

	@Override
	public abstract String getDatumInEuropeesFormaat();

	@Override
	public abstract boolean kleinerDan(Datum d);

	@Override
	public abstract int verschilInJaren(Datum d);

	@Override
	public abstract int verschilInMaanden(Datum d);

	@Override
	public abstract int verschilInDagen(Datum d);

	@Override
	public abstract Datum veranderDatum(int aantalDagen);
	
	
	//Extra methods
	@Override
	public abstract String toString();
	
	@Override
	public abstract boolean equals(Object o);
	
	public abstract int compareTo(Datum otherDate);
}
