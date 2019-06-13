package restaurantManagement1;

import java.io.Serializable;

/**
 * Bill
 * Bill of all transaction info
 * @author Alyssa Gao
 * @version 1.0
 * @date June 13, 2019
 */
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
