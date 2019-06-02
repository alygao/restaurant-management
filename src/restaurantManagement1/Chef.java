package restaurantManagement1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chef extends Employee implements Serializable {

	private TableOrderItem currentOrderItem;
	private List<TableOrderItem> completedOrders = new ArrayList<>();
	private Restaurant restaurant;

	private String name;
	private double pay;
	private String userID;
	private String password;
	private String dateHired;
	private String email;
	private String SINNumber;

	public Chef(String name, double pay, String userID, String password, String dateHired, String email,
			String SINNumber) {
		super(name, pay, userID, password);
		this.dateHired = dateHired;
		this.email = email;
		this.SINNumber = SINNumber;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}