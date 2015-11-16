package model;

import java.math.BigDecimal;

public class Item extends ModelBase {


	private static final long serialVersionUID = 1122921448314180585L;
	
	
	private String titel;
	private BigDecimal verhuurPrijs;
	private ItemType type;
	
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	
	public BigDecimal getVerhuurPrijs() {
		return verhuurPrijs;
	}
	public void setVerhuurPrijs(BigDecimal verhuurPrijs) {
		this.verhuurPrijs = verhuurPrijs;
	}
	
	public ItemType getType() {
		return type;
	}
	public void setType(ItemType type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((verhuurPrijs == null) ? 0 : verhuurPrijs.hashCode());
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
		if (type != other.type)
			return false;
		if (verhuurPrijs == null) {
			if (other.verhuurPrijs != null)
				return false;
		} else if (!verhuurPrijs.equals(other.verhuurPrijs))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Item [titel=" + titel + ", verhuurPrijs=" + verhuurPrijs + ", type=" + type + "]";
	}
	
	
}
