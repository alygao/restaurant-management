package restaurantManagement1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Waiter extends Employee implements Serializable {
	private List<Table> assignedTables;


	public Waiter(String name, double pay, String userID, String password, String dateHired, String email, String SINNumber, String position) {
		super(name, pay, userID, password, dateHired, email, SINNumber, position );
		assignedTables = new ArrayList<>();
	}

	public List<Table> getAssignedTables() {
		return assignedTables;
	}

	public void setAssignedTables(List<Table> assignedTables) {
		this.assignedTables = assignedTables;
	}
}
