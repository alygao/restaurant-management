package restaurantManagement1;

import java.io.Serializable;

public class Table implements Serializable{

	private Table self = this;
	private String tableName;
	private int numSeats;
	private Customer customer;
	private TableOrder currentOrder;
	private Bill bill;
	private boolean canBeReserved;
	private Waiter currentAssignedWaiter;
	private boolean occupied;

	public Table(String tableName, int numSeats, boolean canBeReserved) {
		currentOrder = new TableOrder(this);
		this.tableName = tableName.toUpperCase();
		this.numSeats = numSeats;
		this.canBeReserved = canBeReserved;
		this.customer = null;
		this.currentOrder = null;
		this.bill = null;
		this.currentAssignedWaiter = null;
	}
	
	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableNum) {
		this.tableName = tableNum;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public TableOrder getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(TableOrder currentOrder) {
		this.currentOrder = currentOrder;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public boolean canBeReserved() {
		return canBeReserved;
	}

	public void setCanBeReserved(boolean canBeReserved) {
		this.canBeReserved = canBeReserved;
	}

	public Waiter getCurrentAssignedWaiter() {
		return currentAssignedWaiter;
	}

	public void setCurrentAssignedWaiter(Waiter currentAssignedWaiter) {
		this.currentAssignedWaiter = currentAssignedWaiter;
	}

//	public void placeSeat();

}
