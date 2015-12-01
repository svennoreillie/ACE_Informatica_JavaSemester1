package common.enums;

public enum EnumAddressCity {
	LEUVEN("3000", "Leuven"),
	GENT("9000","Gent"),
	ANTWERPEN("2000","Antwerpen"),
	BRUSSEL("1000","Brussel"),
	HASSELT("3500","Hasselt"),
	GENK("3600", "Genk"),
	AARSCHOT("3200","Aarschot"),
	KORTRIJK("8500","Kortrijk"),
	HEVERLEE("3001","Heverlee");
	
	private String zip;
	private String city;
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	private EnumAddressCity(String zip, String city){
		setZip(zip);
		setCity(city);
	}
}
