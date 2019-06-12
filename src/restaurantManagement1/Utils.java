package restaurantManagement1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils
 * 
 * Formats time and date
 * 
 * @author Alyssa Gao
 * @version 1.0
 * @date June 13, 2019
 */

public class Utils {

	//Variables
	public static String DateFormatPattern = "MMMM d, yyyy";
	public static String TimeFormatPattern = "h:ma";
//	public static String DateFormatPattern = "yyyy-MM-dd";
//	public static String TimeFormatPattern = "HH:mm";

	/**
	 * gets the date formatted
	 * @return the date formatted
	 */
	public static DateTimeFormatter getDateFormatter ( ) {
		return DateTimeFormatter.ofPattern( DateFormatPattern );
	}

	/**
	 * gets the list of dates formatted
	 * @return the list of dates formatted
	 */
	public static ArrayList<DateTimeFormatter> getDateFormatters ( ) {
		ArrayList<DateTimeFormatter> dateFormatters = new ArrayList<>();
		dateFormatters.add( getDateFormatter() );
		return dateFormatters;
	}

	/**
	 * gets the time formatted
	 * @return the time formatted
	 */
	public static DateTimeFormatter getTimeFormatter ( ) {
		return DateTimeFormatter.ofPattern( TimeFormatPattern );
	}

	/**
	 * gets the list of times formatted
	 * @return the list of times formatted
	 */
	public static List<DateTimeFormatter> getTimeFormatters ( ) {
		List<DateTimeFormatter> dateFormatters = new ArrayList<>();
		dateFormatters.add( getTimeFormatter() );
		return dateFormatters;
	}

	/**
	 * converts a string to local date
	 * @param dateString the date in string format
	 * @return the date in LocalDate format
	 */
	public static LocalDate convertToLocalDate ( String dateString ) {
		return LocalDate.parse( dateString, getDateFormatter() );
	}

	/**
	 * converts a string to local time
	 * @param timeString the date in string format
	 * @return the time in LocalTime format
	 */
	public static LocalTime convertToLocalTime ( String timeString ) {
		return LocalTime.parse( timeString.toUpperCase(), DateTimeFormatter.ofPattern( TimeFormatPattern ));
	}


}