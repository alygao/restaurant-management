package restaurantManagement1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TableOrder implements Serializable {
	private String transactionID;
	private Table table;
	private List<TableOrderItem> orderItems;
	private Waiter waiter;
	private String date;
	private double subtotal;
	private double total;
	private boolean paid;
	
	public TableOrder(Table table) {
		this.table = table;
		this.orderItems = new ArrayList<>();
		this.waiter = null;
		this.setSubtotal(0);
		this.setTotal(0);
		this.paid = false;
	}

	public String generateTransactionID(){
		return UUID.randomUUID().toString();
	}

	public String getTransactionID(){
		return transactionID;
	}

	public void setTransactionID(String transactionID){
		this.transactionID = transactionID;
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}

	public List<TableOrderItem>  getOrderItems(){
		return orderItems;
	}

	public void setOrderItems(List<TableOrderItem> orderItems) {
		this.orderItems = orderItems;
	}


	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean hasPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
