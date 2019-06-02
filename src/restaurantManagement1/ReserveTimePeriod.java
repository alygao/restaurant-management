package restaurantManagement1;

import java.io.Serializable;

public class ReserveTimePeriod implements Serializable {
	
	private String date;
	private String time;
	
	public ReserveTimePeriod(String date, String time) {
		this.date = date;
		this.time = time;
//		this.time = convertTimeToDouble(time);
	}
	// Methods
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	
	public double getTimeInDouble() {
		return convertTimeToDouble(time);
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public double convertTimeToDouble(String time) {
		double convertedTime = 0;
		convertedTime = Double.parseDouble(time.substring(0, time.indexOf(":")));
		convertedTime += Double.parseDouble(time.substring(time.indexOf(":"), time.indexOf(":")+2))/60;
		if (time.substring(time.indexOf(":")+3).equals("pm")){
			convertedTime += 12;
		}
		return convertedTime;
	}

}
