package model;

/**
 * Contains the name of a customer.
 * 
 * @author Andre
 *
 */
public class Person {
	
	private String firstName;
	private String lastName;
	
	
	public Person(String first, String last){
		setFirstName(first);
		setLastName(last);
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
}