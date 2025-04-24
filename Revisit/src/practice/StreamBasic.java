package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBasic {
	public static void main(String[] args) {
		List<Integer> myList = new ArrayList();
		myList.add(45);
		myList.add(46);
		myList.add(50);
		myList.add(500);
		List<Integer> evenList = new ArrayList();
		/** Without Stream get all even numbers from the list. */
		for (Integer num : myList) {
			if (num % 2 == 0) {
				evenList.add(num);
			}
		}
		System.out.println("Even list without stream: ");
		System.out.println(evenList);

		/** With Stream get all even numbers from the list. */
		Stream<Integer> streamList = myList.stream();
		List<Integer> filteredList = streamList.filter(i -> i % 2 == 0).collect(Collectors.toList());
		System.out.println(filteredList);

		List<Integer> listWithValuesGreaterThanFifty = myList.stream().filter(i -> i >= 50)
				.collect(Collectors.toList());
		System.out.println(listWithValuesGreaterThanFifty);
		List<Integer> multiList = myList.stream().map(i -> i * 2).collect(Collectors.toList());
		System.out.println(multiList);

		// Stream can be used only once example:
		System.out.println("Logging feature which tells a stream can be used only once: ");
		Stream<Integer> useOnceStream = myList.stream();
//		System.out.println("Stream Count: ");
//		System.out.println(useOnceStream.count());

		Stream<Integer> intStream = useOnceStream.map(i -> i * 3);
		System.out.println("Logging mappedStreamObject: ");
		System.out.println(intStream.collect(Collectors.toList()));
		
	}
}
