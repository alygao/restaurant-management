package restaurantManagement1;

import java.util.List;

public class Table {

	private int tableNum;
	private int numSeats;
	private Customer customer;
	private TableOrder currentOrder;
	private Bill bill;
	private boolean canBeReserved;
	private Waiter currentAssignedWaiter;

	
	
	public Table(int numSeats, boolean canBeReserved) {
		this.numSeats = numSeats;
		this.canBeReserved = canBeReserved;
		this.customer = null;
		this.currentOrder = null;
		this.bill = null;
		this.currentAssignedWaiter = null;
		tableNum = -1;
		
		
		
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
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
