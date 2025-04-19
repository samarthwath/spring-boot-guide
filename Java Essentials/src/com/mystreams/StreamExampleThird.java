package com.mystreams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExampleThird {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 9);
        List<Integer> collectedValue = integerList.stream()
                .filter(intValue -> intValue % 2 == 0)
                .map(value -> value * value)
                .distinct() //to get distinct elements.
                .peek(value -> System.out.println(value)) //peek into the stream for debugging.
                .collect(Collectors.toList());
        System.out.println("List with collected value: ");
        System.out.println(collectedValue);

        //Count numbers which are greater than 5.
        long countNumbersGreaterThanFive = integerList.stream()
                .filter(numValue -> numValue > 5)
                .count();
        System.out.println("Numbers count which are greater than 5: " + countNumbersGreaterThanFive);

        //Sum of the numbers: 
        int sumOfElements = integerList.stream()
                .mapToInt(value -> value)
                .sum();
        System.out.println("Sum of elements: " + sumOfElements);
    }
}
