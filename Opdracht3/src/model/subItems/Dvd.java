/**
 * 
 */
package model.subItems;

import java.math.BigDecimal;
import java.util.Date;

import common.enums.EnumTypeCd;
import common.enums.EnumTypeDvd;
import model.Item;

/**
 * @author Peter
 *
 */
public class Dvd extends model.Item {

	private EnumTypeDvd dvdType;
	
	public EnumTypeDvd getDvdType() {
		return dvdType;
	}

	public void setDvdType(EnumTypeDvd dvdTtype) {
		this.dvdType = dvdTtype;
	}
	
	public Dvd(String titel, BigDecimal verhuurPrijsInEuro, Date beginVerhuurDatum, int verhuurPeriodeInDagen,
			Double verhuurPrijsPerDag, EnumTypeDvd type) {
		super(titel, verhuurPrijsInEuro, beginVerhuurDatum, verhuurPeriodeInDagen, verhuurPrijsPerDag);
		this.setDvdType(type);	
	}

	@Override
	public String toString() {
		return "Dvd [dvdType=" + dvdType + ", " + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dvdType == null) ? 0 : dvdType.hashCode());
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
		Dvd other = (Dvd) obj;
		if (dvdType != other.dvdType)
			return false;
		return true;
	}
	
}
