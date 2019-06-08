package restaurantManagement1;

import java.io.Serializable;

public class TableOrderItem implements Serializable{
	
	private MenuItem menuItem;
	private int quantity;
	private Waiter servedByWaiter;
	private Chef preparedByChef;
	private TableOrder tableOrder;

	TableOrderItem(){

	}

	TableOrderItem(MenuItem menuItem, int quantity, Waiter waiter, Chef chef, TableOrder tableOrder){

	}

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

	
}
