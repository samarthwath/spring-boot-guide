package com.revise.comparators;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorComparableComplete {

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(new Student("1", "Samarth", 98),
                new Student("2", "Pushpak", 95),
                new Student("3", "Hitesh", 100)
        );
        System.out.println("Student List before sorting");
        System.out.println("student list: " + students);
        //Sorting student list by there marks using Comparable Interface:

        Collections.sort(students);
        System.out.println("Student List after sorting by marks: " + students);


        //Sorting student list by there marks using Comparator Interface:
        Collections.sort(students, new StudentMarksComparator());
        System.out.println("student list after sorting by comparator: " + students);

        students.sort(new StudentMarksComparator());
        System.out.println("student list after sorting by comparator: " + students);

        Collections.sort(students, (Student o1, Student o2) -> {
            return o1.getName().compareTo(o2.getName());
        });
        System.out.println("student list after sorting by name: " + students);


        Collections.sort(students, Comparator.comparing(student -> student.getRollNo()));
        System.out.println("student list after sorting by roll number: " + students);


        Collections.sort(students, Comparator.comparingInt(student -> student.getMarks()));
        System.out.println("student list after sorting by marks: " + students);


        List<Student> newStudents = Arrays.asList(new Student("1", "Amit", 100),
                new Student("2", "Ashish", 98),
                new Student("3", "Ankesh", 98)
        );
        System.out.println("newStudents before sorting: " + newStudents);
        newStudents.sort(Comparator.naturalOrder());
        System.out.println("newStudents after sorting: " + newStudents);

        //newStudents.sort(Comparator.reverseOrder());
        //System.out.println("newStudents after sorting by reverse order: " + newStudents);


        newStudents
                .sort(Comparator.comparing((Student student) -> {
                    return student.getMarks();
                }).thenComparing((Student student) -> {
                    return student.getName();
                }));
        System.out.println("newStudents after sorting by thenComparing logic: " + newStudents);

    }

}
