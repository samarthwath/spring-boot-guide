package com.mycollections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuickRecollect {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("Samarth", "2A", 89),
                new Student("Pushpak", "3A", 99),
                new Student("Kishor", "4A", 76)
        );
        System.out.println("Student list before sorting: ");
        System.out.println(studentList);
        Collections.sort(studentList, Comparator.comparingInt((student) -> {
            System.out.println("Log marks: "+student.getMarks());
            return student.getMarks();
        }));
        System.out.println("Student list after sorting by marks: ");
        System.out.println(studentList);
    }
}