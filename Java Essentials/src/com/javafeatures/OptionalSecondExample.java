package com.javafeatures;

import com.mycollections.Student;

import javax.swing.text.html.Option;
import java.util.Optional;

public class OptionalSecondExample {

    public static void main(String[] args) {
        Student student = new Student("Samarth", "2A", 0);
        Optional.ofNullable(student)
                .ifPresent(student1 -> System.out.println("Log studentName: " + student1.getName()));

        Optional.ofNullable(student)
                .ifPresentOrElse((student1) -> {
                            System.out.println("Student is present: " + student1);
                        },
                        () -> {
                            System.out.println("Student object is empty.");
                        }
                );

        Student studentSecond = null;
        Student student1 = Optional.ofNullable(studentSecond)
                .orElse(studentSecond);
        //orElse is safer than get() as get() will throw NPE if the object is null.
        System.out.println("Log student1 object details: " + student1);

        //Below code will give NPE.
        /*Student student2 = Optional.ofNullable(studentSecond)
                .get();*/

        //Below code will throw NPE. But will work if the object is not null.
      /*  Student student2 = Optional.ofNullable(studentSecond)
                .orElseThrow();

        System.out.println("Log student2 object: " + student2);*/

        Optional.ofNullable(student)
                .map(student3 -> student3.getName().toUpperCase())
                .map(uppercaseString -> uppercaseString.trim())
                .ifPresent(value -> System.out.println("Final string: " + value));


        String userEmail = findUserEmail(null).orElse("guest@company.com");
        System.out.println("userEmail: " + userEmail);

    }

    private static Optional<String> findUserEmail(String userName) {
        Optional<String> admin = Optional.ofNullable(userName)
                .map(uName -> uName.startsWith("admin") ? "admin@company.com" : "user@company.com");
        return admin;
    }
}
