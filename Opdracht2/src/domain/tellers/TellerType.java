package domain.tellers;

public enum TellerType {
	CYCLISCH ("Cyclische teller", "domain.tellers.CyclischeTeller"),
	TERUGLOOP ("Terugloop teller", "domain.tellers.TerugLoopTeller");		

	private final String omschrijving;   
	private final String klasseNaam; 
		
	TellerType(String omschrijving, String klasseNaam) {
	    this.omschrijving = omschrijving;
	    this.klasseNaam = klasseNaam;
	}
		
	public String getOmschrijving() { return omschrijving; }
	public String getKlasseNaam() { return klasseNaam; }
}
