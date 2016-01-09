package common.enums;

/**
 * 
 * @author André Nóbrega
 *
 */
public enum EnumAddressStreet {
	LELIELAAN("Lelielaan"),
	STEENSTRAAT("Steenstraat"),
	HOUTHOF("Houthof");
	
	private String address;
	
	private EnumAddressStreet(String address){
		setAddress(address);
	}
	
	public String getAddress(){
		return address;
	}
	private void setAddress(String address){
		this.address = address;
	}
}
