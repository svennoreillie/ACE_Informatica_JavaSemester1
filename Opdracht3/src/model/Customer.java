/**
 * 
 */
package model;

import common.AntiMagicStrings;

/**
 * A customer's details, including name, adress, e-mail and an unique ID.
 * 
 * @author Andre
 *
 */
public class Customer {
	
	private Person person;
	private Address adress;
	private int id;
	private String email;
	private AntiMagicStrings magicString;
	
	public Customer(Person person, Address adress, int id, String email) throws Exception{
		try{
			// TODO er is ergens een fout in deze booleans
			/*
			if (this.adress.getStreet() == "" || this.adress.getNumber() == "" || this.adress.getZip() == "" 
					|| this.adress.getCity() == "" || this.adress.getCountry() == "") throw new Exception(magicString.getAdressNull());
			if (this.person.getFirstName() == "") throw new Exception(magicString.getFirstNameNull());
			if (this.person.getLastName() == "") throw new Exception(magicString.getLastNameNull());
			*/
			setPerson(person);
			setAdress(adress);
			setId(id);
			setEmail(email);
		}
		catch (Exception e){
			throw e;
		}
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Address getAdress() {
		return adress;
	}
	public void setAdress(Address adress) {
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