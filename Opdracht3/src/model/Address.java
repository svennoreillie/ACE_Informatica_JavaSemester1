package model;

/**
 * Contains the adress details of a customer.
 * 
 * @author Andre
 *
 */
public class Address extends ModelBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7901763710456206990L;
	private String street;
	private String number; // ik stel voor dat dit een string blijft
	private String box;
	private String zip;
	private String city;
	private String country;

	public Address(String street, String number, String box, String zip, String city, String country){
		setStreet(street);
		setNumber(number);
		setBox(box);
		setZip(zip);
		setCity(city);
		setCountry(country);
	}
	
	public void setStreet(String s){
		try{
			this.street = s;
		}
		catch (Exception e){
			throw e;
		}
	}
	
	public String getStreet(){
		return this.street;
	}
	
	public void setNumber(String s){
		this.number = s;
	}
	public String getNumber(){
		return this.number;
	}
	
	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.street + " " + this.number);
		if (this.box != ""){
			sb.append(" " + this.box);
		}
		sb.append(", " + this.zip + " " + this.city + ", " +this.country);
		
		return sb.toString();
	}
}
