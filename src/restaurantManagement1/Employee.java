package restaurantManagement1;

import java.io.Serializable;

public abstract class Employee implements Serializable {
	
	private String name;
	private double pay;
	private String userID;
	private String password;
	private String dateHired;
	private String email;
	private String SINNumber;
	private String position;
	
    public Employee(String name, double pay, String userID, String password,  String dateHired, String email, String SINNumber, String position){
    	this.name = name;
    	this.pay = pay;
    	this.userID = userID;
    	this.password = password;
    	this.dateHired = dateHired;
    	this.email = email;
    	this.SINNumber= SINNumber;
    	this.setPosition(position);
        
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

	public String getDateHired() {
		return dateHired;
	}

	public void setDateHired(String dateHired) {
		this.dateHired = dateHired;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSINNumber() {
		return SINNumber;
	}

	public void setSINNumber(String sINNumber) {
		SINNumber = sINNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
