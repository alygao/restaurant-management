package restaurantManagement1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableOrder implements Serializable {
	private Table table;
	private List<TableOrderItem> orderItems;
	private Waiter waiter;
	private double subtotal;
	private double total;

	
	
	public TableOrder(Table table) {
		this.table = table;
		this.orderItems = new ArrayList<>();
		this.waiter = null;
		this.setSubtotal(0);
		this.setTotal(0);
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
}
