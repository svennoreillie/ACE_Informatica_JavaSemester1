package model;

/**
 * 
 * @author Vervoort Peter
 *
 */

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import common.DBException;
import common.DBMissingException;
import common.NotMapped;
import database.*;
import model.ModelBase;

abstract public class Item extends ModelBase {

	private static final long serialVersionUID = 1122921448314180585L;
	
	private String titel;
	private final int boetePrijsPerDag = 3;
	private BigDecimal verhuurPrijsInEuro;
	private Double verhuurPrijsPerDag;
	
	
	public Item(String titel, BigDecimal verhuurPrijsInEuro,Double verhuurPrijsPerDag) {
		this.setTitel(titel);
		this.setVerhuurPrijs(verhuurPrijsInEuro);
		this.setVerhuurPrijsPerDag(verhuurPrijsPerDag);
	}
	
	public Item() {
		this.setTitel("");
		this.setVerhuurPrijsPerDag(0d);
		this.setVerhuurPrijs(new BigDecimal("0"));
	}
	
	public int getBoetePrijsPerDag() {
		return boetePrijsPerDag;
	}
	public void setBoetePrijsPerDag(int boete) {	
	}
	@NotMapped
	public boolean getisUitgeleend() throws NoSuchElementException, DBMissingException, DBException {
		DataService<Uitlening> ds = DataStrategy.getDataService(Uitlening.class);
		return ds.any(u -> u.getUitgeleendItem().equals(this));
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public BigDecimal getVerhuurPrijs() {
		return verhuurPrijsInEuro;
	}
	public void setVerhuurPrijs(BigDecimal verhuurPrijs) {
		this.verhuurPrijsInEuro = verhuurPrijs;
	}
	public Double getVerhuurPrijsPerDag() {
		return verhuurPrijsPerDag;
	}
	public void setVerhuurPrijsPerDag(Double verhuurPrijsPerDag) {
		this.verhuurPrijsPerDag = verhuurPrijsPerDag;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		result = prime * result + ((verhuurPrijsInEuro == null) ? 0 : verhuurPrijsInEuro.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		if (verhuurPrijsInEuro == null) {
			if (other.verhuurPrijsInEuro != null)
				return false;
		} else if (!verhuurPrijsInEuro.equals(other.verhuurPrijsInEuro))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Item [titel=" + titel + ", verhuurPrijs=" + verhuurPrijsInEuro + "]";
	}
	
	public boolean filter(String searchString){
		if(this.titel.toLowerCase().contains(searchString.toLowerCase())) return true;
		return false;
	}
	
}
