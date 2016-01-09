/**
 * 
 */
package model.subItems;

import java.math.BigDecimal;
import common.enums.EnumTypeCd;
import model.Item;

/**
 * 
 * @author Vervoort Peter
 *
 */
public class Cd extends Item {


	private static final long serialVersionUID = -3347605248365779050L;
	private EnumTypeCd cdTtype;

	public EnumTypeCd getType() {
		return cdTtype;
	}

	public void setType(EnumTypeCd setType) {
		this.cdTtype = setType;
	}

	public Cd(String titel, BigDecimal verhuurPrijsInEuro, Double verhuurPrijsPerDag, EnumTypeCd type) {
		super(titel, verhuurPrijsInEuro, verhuurPrijsPerDag);
		setType(type);
	}

	public Cd() {
		super();
		setType(EnumTypeCd.values()[0]);
	}

	@Override
	public String toString() {
		return "Cd [cdTtype= " + cdTtype + ", " + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cdTtype == null) ? 0 : cdTtype.hashCode());
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
		Cd other = (Cd) obj;
		if (cdTtype != other.cdTtype)
			return false;
		return true;
	}

}
