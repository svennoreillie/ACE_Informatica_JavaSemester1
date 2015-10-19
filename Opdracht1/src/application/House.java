package application;

import java.util.List;

public class House {
	private int number;
	private int row;
	private List<Reservation> reservations;
	
	
	
	protected int getNumber() {
		return number;
	}
	protected void setNumber(int number) {
		this.number = number;
	}
	protected int getRow() {
		return row;
	}
	protected void setRow(int row) {
		this.row = row;
	}
	protected List<Reservation> getReservations() {
		return reservations;
	}
	protected void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
}
