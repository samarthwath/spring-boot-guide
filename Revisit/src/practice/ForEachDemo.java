package practice;

import java.util.ArrayList;
import java.util.List;

public class ForEachDemo {
	public static void main(String[] args) {
		List<Integer> myList = new ArrayList();
		myList.add(100);
		myList.add(450);
		myList.add(500);
		myList.add(457);

		myList.forEach(i -> System.out.println(i));
	}
}
