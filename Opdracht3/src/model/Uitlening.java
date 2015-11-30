package model;

import java.util.Date;

public class Uitlening extends ModelBase {

	
	private Date beginVerhuurDatum;
	private int verhuurPeriodeInDagen;
	private Item uitgeleendItem;
	private Customer klantDieUitleent;
	
	
	//TODO setter in try catch blok met in de catch gebruik maken van antiMagicString returns
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
	public Item getUitgeleendItem() {
		return uitgeleendItem;
	}
	public void setUitgeleendItem(Item uitgeleendItem) {
		this.uitgeleendItem = uitgeleendItem;
	}
	public Customer getKlantDieUitleent() {
		return klantDieUitleent;
	}
	public void setKlantDieUitleent(Customer klantDieUitleent) {
		this.klantDieUitleent = klantDieUitleent;
	}
	
	//TODO  clean up
	@Override
	public String toString() {
		return "Uitlening [beginVerhuurDatum=" + beginVerhuurDatum + ", verhuurPeriodeInDagen=" + verhuurPeriodeInDagen
				+ ", uitgeleendItem=" + uitgeleendItem + ", klantDieUitleent=" + klantDieUitleent + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((beginVerhuurDatum == null) ? 0 : beginVerhuurDatum.hashCode());
		result = prime * result + ((klantDieUitleent == null) ? 0 : klantDieUitleent.hashCode());
		result = prime * result + ((uitgeleendItem == null) ? 0 : uitgeleendItem.hashCode());
		result = prime * result + verhuurPeriodeInDagen;
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
		Uitlening other = (Uitlening) obj;
		if (beginVerhuurDatum == null) {
			if (other.beginVerhuurDatum != null)
				return false;
		} else if (!beginVerhuurDatum.equals(other.beginVerhuurDatum))
			return false;
		if (klantDieUitleent == null) {
			if (other.klantDieUitleent != null)
				return false;
		} else if (!klantDieUitleent.equals(other.klantDieUitleent))
			return false;
		if (uitgeleendItem == null) {
			if (other.uitgeleendItem != null)
				return false;
		} else if (!uitgeleendItem.equals(other.uitgeleendItem))
			return false;
		if (verhuurPeriodeInDagen != other.verhuurPeriodeInDagen)
			return false;
		return true;
	}
	
	
	
	

}
