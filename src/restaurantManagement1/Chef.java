package restaurantManagement1;

import java.util.ArrayList;
import java.util.List;

public class Chef extends Employee {

	private TableOrderItem currentOrderItem;
	private List<TableOrderItem> completedOrders = new ArrayList<>();
	private Restaurant restaurant;

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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
