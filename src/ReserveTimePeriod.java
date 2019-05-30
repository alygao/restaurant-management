package restaurantManagement1;

import java.util.Date;

public class ReserveTimePeriod {
	
	private Date date;
	private double time;
	
	public ReserveTimePeriod(Date date, double time) {
		this.date = date;
		this.time = time;
	}
	// Methods
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}

}
