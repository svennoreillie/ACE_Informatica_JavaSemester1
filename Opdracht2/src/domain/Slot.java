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
		for( Teller teVerwijderenTeller : tellerLijst){
			tellerLijst.remove(teVerwijderenTeller);
		}
		for (Teller toeTeVoegenTeller : inTeVoerenTellerLijst){
			tellerLijst.add(toeTeVoegenTeller);
		}
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
	
	public void updateHuidigeWaardeTeller(int tellerPositieInTellerLijst, int huidigeTellerPositie) {
		Teller huidigeTeller = tellerLijst.get(huidigeTellerPositie);
		huidigeTeller.setHuidigeIndex(huidigeTellerPositie);
	}
	
	public int getAantalTellers() {
		return  tellerLijst.size();
	}
	
	public Boolean isGeheimGevonden() {
		if(getSlotCombinatie() == geheimeCode)
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
