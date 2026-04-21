package com.revise.javafeatures.mystreams;

import java.util.*;

public class BasicStreamQuestions {
    public static void main(String[] args) {
        //1. List of Integers find even numbers
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> duplicateElementsList = List.of(1, 2, 2, 3, 3, 4, 5, 6, 7);
        List<Integer> unorderedIntegersList=List.of(9,8,1,2,3,6,7,5);
        List<Integer> evenNumbers = integers
                .stream()
                .filter(num -> num % 2 == 0)
                .toList();
        System.out.println(evenNumbers);

        //2. List of String to Uppercase
        List<String> strings = List.of("samarth", "pushpak", "abhilash");
        List<String> upperCasedStrings = strings
                .stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println(upperCasedStrings);

        //3. Sum of all elements
        Integer sumOfAllElements = integers
                .stream()
                .reduce((v1, v2) -> v1 + v2)
                .orElse(0);
        System.out.println(sumOfAllElements);

        //4. String with Length >5
        List<String> stringLengthGreaterThanX = strings
                .stream()
                .filter(string -> string.length() > 5)
                .toList();
        System.out.println(stringLengthGreaterThanX);

        //5. Remove duplicate elements from list
        List<Integer> removedDuplicates = duplicateElementsList
                .stream()
                .distinct()
                .toList();
        System.out.println(removedDuplicates);

        //6. Sort List of Integer in Ascending and descending order
        List<Integer> ascendingOrderedList = unorderedIntegersList
                .stream()
                .sorted()
                .toList();
        System.out.println(ascendingOrderedList);

        List<Integer> descendingOrderedList = unorderedIntegersList
                .stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(descendingOrderedList);
    }

}
