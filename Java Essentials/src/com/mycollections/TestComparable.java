package com.mycollections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestComparable {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("Shyam", "1A", 74),
                new Student("Suresh", "2A", 77),
                new Student("Kishan", "3A", 76),
                new Student("Himmat", "4A", 75)
        );
        System.out.println("studentList before sorting: " + studentList);
        Collections.sort(studentList);
        System.out.println("Log studentList after sorting: " + studentList);
    }
}
