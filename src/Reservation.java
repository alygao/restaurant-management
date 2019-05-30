package restaurantManagement1;

import java.util.Date;

public class Reservation {
	
	private Table table;
	private Customer customer;
	private ReserveTimePeriod reserveTimePeriod;
	
	
	public Reservation (String name, int numPeople, Date date, double time) {
		this.customer = new Customer (name, numPeople);
		this.table = null; //TODO
		System.out.println("Time " + time);
		this.reserveTimePeriod = new ReserveTimePeriod (date, time);
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
	public ReserveTimePeriod getReserveTimePeriod() {
		return reserveTimePeriod;
	}
	public void setReserveTimePeriod(ReserveTimePeriod reserveTimePeriod) {
		this.reserveTimePeriod = reserveTimePeriod;
	}
	
	
}
