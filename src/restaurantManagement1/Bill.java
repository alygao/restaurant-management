package restaurantManagement1;

import java.io.Serializable;

public class Bill implements Serializable{
	private Customer customer;
	private TableOrder tableOrder;
	private String date;
	private int time;
	private double subtotal;
	private double tax;
	private double total;
	private double tip;
//	private enum status;
	
	public void printBill() {
	
	}
	

}
