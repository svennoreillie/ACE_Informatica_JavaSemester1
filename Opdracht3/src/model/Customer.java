/**
 * 
 */
package model;

/**
 * A customer's details, including name, adress, e-mail and an unique ID.
 * 
 * @author Andre
 *
 */
public class Customer {
	
	private Person person;
	private Adress adress;
	private int id;
	private String email;
	
	public Customer(Person person, Adress adress, int id, String email){
		setPerson(person);
		setAdress(adress);
		setId(id);
		setEmail(email);
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}