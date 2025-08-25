package com.mycollections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorStaticMethods {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("Samarth", "1", 79),
                new Student("Dimple", "2", 89),
                new Student("Dimple", "5", 99),
                new Student("SM", "3", 99),
                new Student("Kishan", "4", 19)
        );
        System.out.println("studentList before sorting: " + studentList);
        Collections.sort(studentList, Comparator.naturalOrder());
        System.out.println("studentList after natural order sorting: " + studentList);
        Collections.sort(studentList, Comparator.reverseOrder());
        System.out.println("studentList after reverse order sorting: " + studentList);

        /*
        Natural ordering means the default sort order of a class — defined by how the class implements the Comparable<T> interface.
         */
        /**
         *
         * Feature	Details
         * naturalOrder()	Sorts in ascending order (default)
         * Applies to	Objects that implement Comparable<T>
         * Based on	Their compareTo() method
         * Reverse version	Comparator.reverseOrder()
         */
        List<String> list = Arrays.asList("Bob", "Geeks", "Alice");
        System.out.println("Unsorted list: " + list);
        Collections.sort(list, Comparator.naturalOrder());
        System.out.println("sortedList: " + list);
        Collections.sort(list, Comparator.reverseOrder());
        System.out.println("sortedList in descending order: " + list);

        studentList.sort(Comparator.comparing(student -> {
            return student.getName();
        }));

        System.out.println("studentList after sorting by name in ascending order: ");
        System.out.println(studentList);

       /* studentList.sort(
                Comparator.comparing(Student::getName)
                        .thenComparing(Student::getMarks)
        );*/

        studentList.sort(
                Comparator.comparing((Student student) -> {
                            return student.getName();
                        })
                        .thenComparing((Student student) -> {
                            return student.getRollNo();
                        })
        );

        System.out.println("studentList after sorting by name in ascending order and then by rollNo in ascending order: ");
        System.out.println(studentList);

    }
}
