package com.revise.javafeatures;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        //Predicate is a functional interface in java.
        //It represents Boolean valued function

        Predicate<Integer> evenPredicate = number -> number % 2 == 0;
        Predicate<Integer> oddPredicate = number -> number % 2 == 0;

        System.out.println(evenPredicate.test(100));
        System.out.println(oddPredicate.test(178));
        System.out.println(evenPredicate.test(133));


        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("integers before: " + integers);
        List<Integer> integers1 = integers
                .stream()
                .filter(evenPredicate)
                .toList();
        System.out.println("integers after: " + integers1);

    }

}
