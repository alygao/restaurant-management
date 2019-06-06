package restaurantManagement1;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<String> words = new DoublyLinkedList<>();
		
		words.add("cat");
		words.add("dog");
		words.add("bird");
		words.add("cow");
		
		System.out.println(words.get(2));
		System.out.println(words.indexOf("bird"));
		String word = "a a a";
		System.out.println(word.replaceAll(" ", ""));

	}

}
