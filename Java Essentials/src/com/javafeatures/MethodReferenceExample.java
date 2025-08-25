package com.javafeatures;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceExample {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("Sam", "Kishan", "Amrit");
        System.out.println("Log string list before: ");
        System.out.println(stringList);
        stringList.forEach(string -> System.out.println(string));

        //Types of method references.
        //Reference to an instance method of a particular object
        stringList.forEach(System.out::println);

        




    }
}
