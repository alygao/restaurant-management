package restaurantManagement1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utils {

	public static LocalDate convertToLocalDate ( String dateString ) {
		System.out.println( "dateString = " + dateString );
		return LocalDate.parse( dateString, DateTimeFormatter.ofPattern( "MMMM d, yyyy" ) );
	}
	
	public static LocalTime convertToLocalTime ( String timeString ) {
		return LocalTime.parse( timeString.toUpperCase(), DateTimeFormatter.ofPattern( "h:ma" ));
	}


}
