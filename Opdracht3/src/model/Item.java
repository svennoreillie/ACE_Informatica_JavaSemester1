package model;

import java.math.BigDecimal;
import java.util.Date;


abstract public class Item extends ModelBase {


	private static final long serialVersionUID = 1122921448314180585L;
	
	private String titel;
	

	private BigDecimal verhuurPrijsInEuro;
	private Date beginVerhuurDatum;
	private int verhuurPeriodeInDagen;
	private Double verhuurPrijsPerDag;
	
	
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
	public Date getBeginVerhuurDatum() {
		return beginVerhuurDatum;
	}
	public void setBeginVerhuurDatum(Date beginVerhuurDatum) {
		this.beginVerhuurDatum = beginVerhuurDatum;
	}
	public int getVerhuurPeriodeInDagen() {
		return verhuurPeriodeInDagen;
	}
	public void setVerhuurPeriodeInDagen(int verhuurPeriodeInDagen) {
		this.verhuurPeriodeInDagen = verhuurPeriodeInDagen;
	}
	public Double getVerhuurPrijsPerDag() {
		return verhuurPrijsPerDag;
	}
	public void setVerhuurPrijsPerDag(Double verhuurPrijsPerDag) {
		this.verhuurPrijsPerDag = verhuurPrijsPerDag;
	}
	
	public Item(String titel, BigDecimal verhuurPrijsInEuro, Date beginVerhuurDatum, int verhuurPeriodeInDagen,
			Double verhuurPrijsPerDag) {
		this.setTitel(titel);
		this.setVerhuurPrijs(verhuurPrijsInEuro);
		this.setBeginVerhuurDatum(beginVerhuurDatum);
		this.setVerhuurPeriodeInDagen(verhuurPeriodeInDagen);
		this.setVerhuurPrijsPerDag(verhuurPrijsPerDag);
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
