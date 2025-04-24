package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ListExamples {
	public static void main(String[] args) {
		final List myList = new ArrayList();
		myList.add(0, "Hello");
		myList.add(1, true);
		myList.add(2, false);
		myList.add(3, null);
		myList.add(4, "Hello");
		System.out.println("Logging myList values: ");
		System.out.println(myList);
		myList.remove(3);
		System.out.println("Logging myList values again: ");
		System.out.println(myList);
		System.out.println(myList.hashCode());
		System.out.println(myList.isEmpty());
		System.out.println(myList.size());
		Consumer consumer = new Consumer() {
			@Override
			public void accept(Object t) {
				System.out.println((Integer) t);
			}
		};
		List al = new ArrayList();
		al.add(450);
		al.add(100);
		al.forEach(consumer);
		al.forEach(i -> System.out.println(i));
	}
}
