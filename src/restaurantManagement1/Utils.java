package restaurantManagement1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static String DateFormatPattern = "MMMM d, yyyy";
	public static String TimeFormatPattern = "h:ma";
//	public static String DateFormatPattern = "yyyy-MM-dd";
//	public static String TimeFormatPattern = "HH:mm";

	public static DateTimeFormatter getDateFormatter ( ) {
		return DateTimeFormatter.ofPattern( DateFormatPattern );
	}

	public static ArrayList<DateTimeFormatter> getDateFormatters ( ) {
		ArrayList<DateTimeFormatter> dateFormatters = new ArrayList<>();
		dateFormatters.add( getDateFormatter() );
		return dateFormatters;
	}

	public static DateTimeFormatter getTimeFormatter ( ) {
		return DateTimeFormatter.ofPattern( TimeFormatPattern );
	}

	public static List<DateTimeFormatter> getTimeFormatters ( ) {
		List<DateTimeFormatter> dateFormatters = new ArrayList<>();
		dateFormatters.add( getTimeFormatter() );
		return dateFormatters;
	}

	public static LocalDate convertToLocalDate ( String dateString ) {
		System.out.println( "dateString = " + dateString );
		return LocalDate.parse( dateString, getDateFormatter() );
	}

	public static LocalTime convertToLocalTime ( String timeString ) {
		return LocalTime.parse( timeString.toUpperCase(), DateTimeFormatter.ofPattern( TimeFormatPattern ));
	}


}