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
	private Adress adress;
	private String email;
	private boolean spam = false;
	private AntiMagicStrings magicString;
	
	public Customer(Person person, Adress adress, String email) throws Exception{
		try{
			setPerson(person);
			setAdress(adress);
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
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getSpam(){
		return spam;
	}
	public void setSpam(boolean bool){
		this.spam = bool;
	}
}