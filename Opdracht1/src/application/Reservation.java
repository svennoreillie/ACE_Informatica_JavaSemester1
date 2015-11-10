package application;

import model.Date;
import model.Months;

public class Reservation {
	private Date startDate;
	private int numberOfDays;
	private Person person;
	private House house;
	
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((house == null) ? 0 : house.hashCode());
		result = prime * result + numberOfDays;
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(Integer.toString(this.getHouse().getDisplayNumber()));
		s.append(",");
		try {
			s.append(String.format("%02d", this.getStartDate().getDay()));
			s.append("/");
			s.append(String.format("%02d", this.getStartDate().getMonth()));
			s.append("/");
			s.append(String.format("%04d", this.getStartDate().getYear()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.append(",");
		s.append(Integer.toString(this.getNumberOfDays()));
		s.append(">");
		s.append(this.getPerson().getLastName());
		s.append(",");
		s.append(this.getPerson().getFirstName());
		
		
		return s.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (house == null) {
			if (other.house != null)
				return false;
		} else if (!house.equals(other.house))
			return false;
		if (numberOfDays != other.numberOfDays)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
	
}
