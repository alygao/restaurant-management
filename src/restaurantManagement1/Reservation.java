package restaurantManagement1;

import java.io.Serializable;

public class Reservation implements Serializable {
	
	private Table table;
	private Customer customer;
	private ReservationDateTime reservationDateTimePeriod;
	
	
//	public Reservation (String name, int numPeople, Date date, double time) {
//		this.customer = new Customer (name, numPeople);
//		this.table = null; //TODO
//		System.out.println("Time " + time);
//		this.reserveTimePeriod = new ReserveTimePeriod (date, time);
//	}
	
	public Reservation (Table table, String name, int numPeople, ReservationDateTime reservationDateTimePeriod) {
		this.table = table;
		this.customer = new Customer (name, numPeople);
		this.reservationDateTimePeriod = reservationDateTimePeriod;
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
	
	
}
