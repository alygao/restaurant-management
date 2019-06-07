package restaurantManagement1;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDateTime implements Serializable {
	
	private String date;
	private String time;
	
	public ReservationDateTime(String date, String time) {
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
	
//	public double getTimeInDouble() {
//		return convertTimeToDouble(time);
//	}
	public void setTime(String time) {
		this.time = time;
	}
	
//	public double convertTimeToDouble(String time) {
//		double convertedTime = 0;
//		convertedTime = Double.parseDouble(time.substring(0, time.indexOf(":")));
//		convertedTime += Double.parseDouble(time.substring(time.indexOf(":"), time.indexOf(":")+2))/60;
//		if (time.substring(time.indexOf(":")+3).equals("pm")){
//			convertedTime += 12;
//		}
//		return convertedTime;
//	}
	
	public LocalDate getLocalDate ( ) {
		return Utils.convertToLocalDate( this.date);
	}
	
	public LocalTime getLocalTime ( ) {
		return Utils.convertToLocalTime( this.time );
	}
	
	public LocalTime getSecuredTimePeriodFrom ( ) {
		LocalTime localTime = getLocalTime ( );
		int hour = localTime.getHour() - 2;
		if ( hour < 0 ) {
			hour = 0; // we will not adjust the date as the restaurant will not across the midnight.
		}
		return LocalTime.of(hour, localTime.getMinute() );
	}

	public LocalTime getSecuredTimePeriodTo ( ) {
		LocalTime localTime = getLocalTime ( );
		int hour = localTime.getHour() + 2;
		if ( hour > 23 ) {
			hour = 23;	// we will not adjust the date as the restaurant will not across the midnight.
		}
		return LocalTime.of(hour, localTime.getMinute() );
	}
}
