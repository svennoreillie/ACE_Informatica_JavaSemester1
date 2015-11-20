/**
 * 
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

import common.enums.EnumTypeCd;

/**
 * @author Peter
 *
 */
public class Cd extends Item {

	private EnumTypeCd cdTtype;

	public EnumTypeCd getType() {
		return cdTtype;
	}

	public void setType(EnumTypeCd setType) {
			this.cdTtype = setType;
	}
	
	public Cd(String titel, BigDecimal verhuurPrijsInEuro, Date beginVerhuurDatum, int verhuurPeriodeInDagen,
			Double verhuurPrijsPerDag, EnumTypeCd type) {
		super(titel, verhuurPrijsInEuro, beginVerhuurDatum, verhuurPeriodeInDagen, verhuurPrijsPerDag);
		this.setType(type);
	}
}
