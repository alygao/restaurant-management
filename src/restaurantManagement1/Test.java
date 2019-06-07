package restaurantManagement1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Test {

	public static void main(String[] args) {
//		List<String> words = new DoublyLinkedList<>();
//		
//		words.add("cat");
//		words.add("dog");
//		words.add("bird");
//		words.add("cow");
//		
//		System.out.println(words.get(2));
//		System.out.println(words.indexOf("bird"));
//		String word = "a a a";
//		System.out.println(word.replaceAll(" ", ""));
		
//		String timeString = "1:3pm";
//		LocalTime time = LocalTime.parse( timeString.toUpperCase(), DateTimeFormatter.ofPattern( "h:ma" ));
//		System.out.println( "time = " + time );
		
		
//		System.out.println( LocalDate.of( 2019, -1, -2 ) );
		String dateString = "June 7, 2019";
		LocalDate date = LocalDate.parse( dateString, DateTimeFormatter.ofPattern( "MMMM d, yyyy" ));
		System.out.println( "date = " + date );

	}

}
