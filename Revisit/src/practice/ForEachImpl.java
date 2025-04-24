package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ForEachImpl {
	public static void main(String[] args) {
		List<Integer> myList = new ArrayList();
		myList.add(100);
		myList.add(450);
		myList.add(500);
		myList.add(457);

		Consumer<Integer> consumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer i) {
				System.out.println(i);
			}
		};
		myList.forEach(consumer);
	}
}
