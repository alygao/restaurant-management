package restaurantManagement1;

import java.io.Serializable;
import java.util.ArrayList;

public class TableOrderItem implements Serializable{
	
	private MenuItem menuItem;
	private int quantity;
	private Waiter servedByWaiter;
	private Chef preparedByChef;
	private TableOrder tableOrder;
	private boolean fired;
	private boolean servedToCustomer;

	
	
	public TableOrderItem(MenuItem menuItem, Waiter servedByWaiter, TableOrder tableOrder) {
		this.menuItem = menuItem;
		this.servedByWaiter = servedByWaiter;
		this.tableOrder = tableOrder;
		this.quantity = 0;
		fired = false;
		servedToCustomer = false;
	}

	public TableOrderItem(MenuItem menuItem, int quantity, Waiter waiter, Chef chef, TableOrder tableOrder){
		this.menuItem = menuItem;
		this.quantity = quantity;
		this.servedByWaiter = waiter;
		this.preparedByChef = chef;
		this.tableOrder = tableOrder;
		fired = false;
		servedToCustomer = false;
	}

//	TableOrderItem(MenuItem menuItem, int quantity, Waiter waiter, Chef chef){
//		this.menuItem = menuItem;
//		this.quantity = quantity;
//		this.servedByWaiter = waiter;
//		this.preparedByChef = chef;
//		this.tableOrder = tableOrder;
//		fired = false;
//	}

	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Waiter getServedByWaiter() {
		return servedByWaiter;
	}
	public void setServedByWaiter(Waiter servedByWaiter) {
		this.servedByWaiter = servedByWaiter;
	}
	public Chef getPreparedByChef() {
		return preparedByChef;
	}
	public void setPreparedByChef(Chef preparedByChef) {
		this.preparedByChef = preparedByChef;
	}
	public TableOrder getTableOrder() {
		return tableOrder;
	}
	public void setTableOrder(TableOrder tableOrder) {
		this.tableOrder = tableOrder;
	}

	public boolean isFired() {
		return fired;
	}

	public void setFired(boolean fired) {
		this.fired = fired;
	}

	public boolean isServedToCustomer() {
		return servedToCustomer;
	}

	public void setServedToCustomer(boolean servedToCustomer) {
		this.servedToCustomer = servedToCustomer;
	}

	
}
