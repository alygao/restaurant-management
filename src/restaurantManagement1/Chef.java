package restaurantManagement1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chef extends Employee implements Serializable {

	private TableOrderItem currentOrderItem;
	private List<TableOrderItem> completedOrders;

	public Chef(String name, double pay, String userID, String password, String dateHired, String email, String SINNumber, String position) {
		super(name, pay, userID, password, dateHired, email, SINNumber, position );
		currentOrderItem = null;
		completedOrders = new ArrayList<>();
	}

	public void takeOrderItem() {

	}

	public void completeOrderItem() {

	}

	public TableOrderItem getCurrentOrderItem() {
		return currentOrderItem;
	}

	public void setCurrentOrderItem(TableOrderItem currentOrderItem) {
		this.currentOrderItem = currentOrderItem;
	}

	public List<TableOrderItem> getCompletedOrders() {
		return completedOrders;
	}

	public void setCompletedOrders(List<TableOrderItem> completedOrders) {
		this.completedOrders = completedOrders;
	}


}