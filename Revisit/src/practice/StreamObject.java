package practice;

import java.util.stream.Stream;

public class StreamObject {
	public static void main(String[] args) {
		// 1. Empty Stream
		Stream<Object> emptyStream = Stream.empty();

		String[] names = { "Samarth", "Pushpak", "Minakshi" };
		
		//2. array, object, collection
		Stream<String> namesStream = Stream.of(names);
		namesStream.forEach(e -> System.out.println(e));
		
		//3. builder
		Stream<Object> streamBuilder=Stream.builder().build();
		
		
	}
}
