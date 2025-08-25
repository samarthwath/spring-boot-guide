package com.mycollections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestComparator {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("Shyam", "1A", 74),
                new Student("Suresh", "2A", 77),
                new Student("Kishan", "3A", 76),
                new Student("Himmat", "4A", 75)
        );
        System.out.println("studentList before sorting: " + studentList);
        StudentMarksComparator studentMarksComparator = new StudentMarksComparator();
        Collections.sort(studentList, studentMarksComparator);
        System.out.println("studentList after sorting: " + studentList);

        Collections.sort(studentList, (Student studentFirst, Student studentSecond) -> {
            return studentFirst.getName().compareTo(studentSecond.getName());
        });
        System.out.println("studentList after sorting by name: " + studentList);


    }
}
