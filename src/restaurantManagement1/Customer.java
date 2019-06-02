package restaurantManagement1;

import java.io.Serializable;

public class Customer implements Serializable{
	
	private String name;
	private int numPeople;

	public Customer(String name, int numPeople) {
		this.name = name.toUpperCase();
		this.numPeople = numPeople;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}
}
