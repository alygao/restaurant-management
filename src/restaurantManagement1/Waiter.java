package restaurantManagement1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Waiter extends Employee implements Serializable {
	private List<Table> assignedTables = new ArrayList<>();
	private Restaurant restaurant;

	private String name;
	private double pay;
	private String userID;
	private String password;
	private String dateHired;
	private String email;
	private String SINNumber;

	Waiter(String name, double pay, String userID, String password, String dateHired, String email, String SINNumber) {
		super(name, pay, userID, password);
		this.dateHired = dateHired;
		this.email = email;
		this.SINNumber = SINNumber;
	}

}
