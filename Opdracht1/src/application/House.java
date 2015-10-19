package application;

import java.util.List;

public class House {
	private int number;
	private int row;
	private List<Reservation> reservations;
	
	public House() {
		
	}
	
	public House(int displayNumber) throws Throwable {
		this.setDisplayNumber(displayNumber);
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public int getDisplayNumber() {
		return (getRow() * 100) + getNumber();
	}
	
	public void setDisplayNumber(int displayNumber) throws Exception {
		if (displayNumber < 0 || displayNumber > 9999) throw new Exception("DisplayNumber not in valid range 1 - 9999");
		this.setRow(displayNumber / 100);
		this.setNumber(displayNumber % 100);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((reservations == null) ? 0 : reservations.hashCode());
		result = prime * result + row;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		House other = (House) obj;
		if (number != other.number)
			return false;
		if (reservations == null) {
			if (other.reservations != null)
				return false;
		} else if (!reservations.equals(other.reservations))
			return false;
		if (row != other.row)
			return false;
		return true;
	}
}
