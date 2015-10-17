package application;

import model.Date;

public class Reservation {
	private Date startDate;
	private int numberOfDays;
	private Person person;
	private House house;
	
	protected Date getStartDate() {
		return startDate;
	}
	protected void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	protected int getNumberOfDays() {
		return numberOfDays;
	}
	protected void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	protected Person getPerson() {
		return person;
	}
	protected void setPerson(Person person) {
		this.person = person;
	}
	protected House getHouse() {
		return house;
	}
	protected void setHouse(House house) {
		this.house = house;
	}
}
