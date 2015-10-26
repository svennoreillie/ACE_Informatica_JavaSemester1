package domain;

import java.util.List;

import Helpers.NotImplementedException;
import domain.tellers.Teller;

public class Slot {

	private String geheimeCode = "";
	
	public Slot(List<Teller> tellers, String naam) {
		throw new NotImplementedException();
	}
	
	public char getWaardeTeller(int index) {
		throw new NotImplementedException();
	}
	
	public List<Teller> getTellers() {
		throw new NotImplementedException();
	}
	
	public String getSlotCombinatie() {
		throw new NotImplementedException();
	}
	
	public void updateHuidigeWaardeTeller(int index) {
		throw new NotImplementedException();
	}
	
	public int getAantalTellers() {
		throw new NotImplementedException();
	}
	
	public Boolean isGeheimGevonden() {
		throw new NotImplementedException();
	}
	
	public void resetTellers() {
		throw new NotImplementedException();
	}
	
}
