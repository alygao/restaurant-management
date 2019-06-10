package restaurantManagement1;

import java.io.Serializable;

public class Reservation implements Serializable {
	
	private Table table;
	private Customer customer;
	private ReservationDateTime reservationDateTimePeriod;
	private boolean claimed;
	
	
	public Reservation (Table table, String name, int numPeople, ReservationDateTime reservationDateTimePeriod) {
		this.table = table;
		this.customer = new Customer (name, numPeople);
		this.reservationDateTimePeriod = reservationDateTimePeriod;
		claimed = false;
	}
	
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public ReservationDateTime getReservationDateTime() {
		return reservationDateTimePeriod;
	}
	public void setReservationDateTime(ReservationDateTime reservationDateTimePeriod) {
		this.reservationDateTimePeriod = reservationDateTimePeriod;
	}

	public boolean isClaimed() {
		return claimed;
	}

	public void setClaimed(boolean claimed) {
		this.claimed = claimed;
	}
	
	
	
}
