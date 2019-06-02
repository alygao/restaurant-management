package restaurantManagement1;

import java.io.Serializable;

public abstract class Employee implements Serializable {
	
    private String name;
   	private double pay;
    private String userID;
    private String password;

    public Employee(String name, double pay, String userID, String password){
    	this.name = name;
    	this.pay = pay;
    	this.userID = userID;
    	this.password = password;
        
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
