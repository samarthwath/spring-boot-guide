package com.revise.core.java.mycollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorStaticMethods {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Samarth", 100));
        studentList.add(new Student(2, "Pushpak", 200));
        studentList.add(new Student(3, "Demo", 300));
        studentList.add(new Student(4, "Demo", 400));
        System.out.println("studentList before: " + studentList);
        //Natural order takes the order in which comparable is implemented for the object
        Collections.sort(studentList, Comparator.naturalOrder());
        System.out.println("studentList after: " + studentList);
        Collections.sort(studentList, Comparator.reverseOrder());
        System.out.println("studentList in descending order: " + studentList);
        Collections.sort(studentList, Comparator.comparing((student) -> student.getMarks()));
        System.out.println("studentList after sorting with marks: " + studentList);
        Collections.sort(studentList, Comparator.comparing(student -> student.getName()));
        System.out.println("studentList after sorting with name: " + studentList);
        studentList.sort(Comparator.comparing((Student student) -> {
                    return student.getName();
                }).thenComparing((Student student) -> {
                    return student.getId();
                })
        );
        System.out.println("studentList after sorting with name and breaking ties with id: " + studentList);
    }
}
