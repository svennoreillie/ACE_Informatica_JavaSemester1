package domain;

import java.util.List;

import Helpers.NotImplementedException;
import domain.tellers.Teller;

public class Slot {

	private String geheimeCode;
	private List <Teller> tellerLijst;
	
	public Slot(List<Teller> tellers, String geheimeCode) {
		setTellers(tellers);
		setGeheimeCode(geheimeCode);
	}
	
	public char getWaardeTeller(int tellerPositieInTellerLijst) {
		Teller huidigeTeller = tellerLijst.get(tellerPositieInTellerLijst);
		return  huidigeTeller.getHuidigeWaarde();
	}
	
	public List<Teller> getTellers() {
		return tellerLijst;
	}
	
	private void setTellers (List<Teller> inTeVoerenTellerLijst){
		this.tellerLijst = inTeVoerenTellerLijst;
	}
	
	private void setGeheimeCode (String nieuweGeheimeCode){
		geheimeCode = nieuweGeheimeCode;
	}
	
	public String getSlotCombinatie() {
		StringBuffer slotCombinatie = new StringBuffer();
		for (Teller huidigeTeller : tellerLijst){
			slotCombinatie.append(huidigeTeller.getHuidigeWaarde());
		}
		return slotCombinatie.toString();
	}
	
	//Methode moet nagekeken worden, toegevoegd vanwege fout in slotcontroller (uml geeft slechts 1 parameter aan) 
	public void updateHuidigeWaardeTeller(int tellerPositieInTellerLijst) {
		Teller huidigeTeller = tellerLijst.get(tellerPositieInTellerLijst);
		huidigeTeller.updateHuidigeWaarde();
	}
	
	//denk dat deze weg mag
	public void updateHuidigeWaardeTeller(int tellerPositieInTellerLijst, int huidigeTellerPositie) {
		Teller huidigeTeller = tellerLijst.get(tellerPositieInTellerLijst);
		huidigeTeller.setHuidigeIndex(huidigeTellerPositie);
	}
	
	public int getAantalTellers() {
		return  tellerLijst.size();
	}
	
	public Boolean isGeheimGevonden() {
		if(getSlotCombinatie().equals(geheimeCode))
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	public void resetTellers() {
		for (Teller huidigeTeller : tellerLijst){
			huidigeTeller.resetHuidigeWaarde();
		}
	}
	
}
