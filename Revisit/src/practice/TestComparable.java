package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestComparable {
	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Samarth Wath", 750));
		students.add(new Student("Pushpak Wath", 470));
		students.add(new Student("Amit Halder", 780));
		System.out.println("List Befor sorting: ");
		System.out.println(students);
		Collections.sort(students);
		System.out.println("List after Sorting: ");
		System.out.println(students);
	}
}
