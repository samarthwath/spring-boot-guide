package com.revise.core.java.mycollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparableTest {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Samarth", 100));
        studentList.add(new Student(2, "Pushpak", 200));
        studentList.add(new Student(3, "Demo", 300));
        System.out.println("studentList before: " + studentList);
        //List sorted using comparable
        //        Collections.sort(studentList);
//        System.out.println("studentList after: " + studentList);

        //List sorted using comparator
        //Below is the traditional way:
        Collections.sort(studentList, (s1, s2) -> {
            if (s1.getMarks() < s2.getMarks()) {
                return -1;
            } else if (s1.getMarks() > s2.getMarks()) {
                return 1;
            } else {
                return 0;
            }
        });

        Collections.sort(studentList, (studentFirst, studentSecond) -> Integer.compare(studentFirst.getMarks(), studentSecond.getMarks()));

        System.out.println("studentList after: " + studentList);
    }
}
