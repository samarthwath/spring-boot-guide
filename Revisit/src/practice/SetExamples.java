package practice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExamples {
	public static void main(String[] args) {
		int a = 45;
		Set setObject = new HashSet();
		setObject.add(12);
		setObject.add(450);
		setObject.add(a);
		setObject.add(542);
		System.out.println("Logging setObject which got createed using HashSet: ");
		System.out.println(setObject);
		Iterator it = setObject.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		Set setObjectSecond = new LinkedHashSet();
		setObjectSecond.add("Hello");
		setObjectSecond.add("World");
		setObjectSecond.add("Hey Geeks.");
		System.out.println("Logging setObjectSecond which got created using LinkedHashSet: ");
		System.out.println(setObjectSecond);

		Set setObjectThird = new TreeSet();
		setObjectThird.add("Hello");
		setObjectThird.add("World");
		setObjectThird.add("Hey Geeks.");
		System.out.println("Logging setObjectThird which got created using TreeSet: ");
		System.out.println(setObjectThird);
	}
}
