package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import model.ModelBase;


abstract public class Item extends ModelBase {

	private static final long serialVersionUID = 1122921448314180585L;
	
	private String titel;
	private final int boetePrijsPerDag = 3;
	private BigDecimal verhuurPrijsInEuro;
	private Double verhuurPrijsPerDag;
	private boolean isUitgeleend;
	
	
	public Item(String titel, BigDecimal verhuurPrijsInEuro,Double verhuurPrijsPerDag) {
		this.setTitel(titel);
		this.setVerhuurPrijs(verhuurPrijsInEuro);
		this.setVerhuurPrijsPerDag(verhuurPrijsPerDag);
		this.isUitgeleend = false;
	}
	
	public Item() {
		this.setTitel("");
		this.setVerhuurPrijsPerDag(0d);
		this.setVerhuurPrijs(new BigDecimal("0"));
		this.isUitgeleend = false;
	}
	
	
	public int getBoetePrijsPerDag() {
		return boetePrijsPerDag;
	}
	public void setBoetePrijsPerDag(int boete) {
		//Hier doen we niets
		
	}
	public boolean getisUitgeleend() {
		return isUitgeleend;
	}
	public void setisUitgeleend(boolean isUitgeleend) {
		this.isUitgeleend = isUitgeleend;
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
	
}
