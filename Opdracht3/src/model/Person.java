package model;

/**
 * Contains the name of a customer.
 * 
 * @author André Nóbrega
 *
 */
public class Person extends ModelBase {
	
	private String firstName;
	private String lastName;
	
	
	public Person(String first, String last){
		setFirstName(first);
		setLastName(last);
	}
	
	public Person() {
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString(){
		return this.firstName + " " + this.lastName;
	}
	
	public boolean filter(String searchString) {
		if (this.getFirstName().contains(searchString)) return true;
		if (this.getLastName().contains(searchString)) return true;
		return false;
	}
}
