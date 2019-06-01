package restaurantManagement1;

public class ReserveTimePeriod {
	
	private String date;
	private double time;
	
	public ReserveTimePeriod(String date, String time) {
		this.date = date;
		this.time = convertTimeToDouble(time);
	}
	// Methods
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = convertTimeToDouble(time);
	}
	public void setTime(double time) {
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
