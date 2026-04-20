package com.revise.javafeatures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MethodReferences {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Hello", "Hi", "Geeks", "Hey");
        strings.forEach(string -> System.out.println(string));

        //Static Method references:
        System.out.println("list length: " + strings.size());
        strings.forEach(MethodReferences::print);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        integers.forEach(MethodReferences::square);

        List<Double> doubles = Arrays.asList(1.2, 2.3, 3.4, 4.5, 5.6, 6.7, 7.8, 8.9, 9.0);

        MyPrinter myPrinter = new MyPrinter();
        //Reference to an Instance method of a particular object
        doubles.forEach(myPrinter::print);


        //Reference to an Instance method of an arbitary object.
        strings.
                stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);


        List<Person> personList = Arrays.asList(new Person(12, "Samarth Wath"),
                new Person(21, "Pushpak Wath"),
                new Person(22, "Demo")
        );
        System.out.println("personList before: " + personList);
        Collections.sort(personList, (p1, p2) -> {
            return p1.getName().compareTo(p2.getName());
        });
        System.out.println("personList after sorting: " + personList);

        System.out.println("strings before sorting: " + strings);
        Collections.sort(strings, String::compareTo);
        System.out.println("strings after sorting: " + strings);


        //Sum of Squares of Even numbers using Streams and Method Reference

        
    }

    private static void print(String name) {
        System.out.println(name);
    }

    private static void square(int number) {
        System.out.println(number * number);
    }


}
