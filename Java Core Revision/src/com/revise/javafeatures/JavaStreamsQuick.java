package com.revise.javafeatures;

import java.util.*;
import java.util.stream.Collectors;

public class JavaStreamsQuick {
    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional<Integer> reducedValue = integerList
                .stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * number)
                .reduce((n1, n2) -> n1 + n2);

        reducedValue.ifPresentOrElse(System.out::println, () -> {
            System.out.println("reduced value is null");
        });

        reducedValue.ifPresentOrElse((value) -> {
            System.out.println("reduced value obtained " + value);
        }, () -> {
            System.out.println("reduced value is null");
        });

        List<Transaction> transactions = List.of(new Transaction(1, "grocery", 1500),
                new Transaction(2, "miscellaneous", 2500),
                new Transaction(3, "needs", 26000),
                new Transaction(4, "needs", 100),
                new Transaction(5, "grocery", 5000),
                new Transaction(6, "miscellaneous", 3500)
        );

        transactions
                .stream()
                .map(Transaction::getAmount)
                .reduce((amount1, amount2) -> amount1 + amount2)
                .ifPresent(System.out::println);

        int grocery = transactions
                .stream()
                .filter(transaction -> transaction.getName().equals("grocery"))
                .mapToInt(Transaction::getAmount)
                .sum();
        System.out.println("Grocery expenses: " + grocery);


        OptionalDouble average = transactions
                .stream()
                .mapToInt(Transaction::getAmount)
                .average();
        System.out.println("Average grocery expenses: " + average.getAsDouble());


        List<Integer> duplicateIntegers = List.of(1, 2, 3, 3, 4, 4, 5, 6);
        Optional<Integer> reduce = Optional.of(duplicateIntegers
                .stream()
                .filter(number -> number % 2 == 0)
                .distinct()
                .mapToInt(number -> number * number)
                .sum());

        System.out.println(reduce.get());

        long count = integerList
                .stream()
                .filter(number -> number > 5)
                .count();

        System.out.println("Number greater than 5: " + count);

        //Sum of all distinct numbers:

        Optional<Integer> reduce1 = duplicateIntegers
                .stream()
                .distinct()
                .reduce((number1, number2) -> number1 + number2);

        System.out.println("Sum of all distinct number: " + reduce1.get());

        //Even numbers and sort them
        List<Integer> integers = integerList
                .stream()
                .filter(number -> number % 2 == 0)
                .sorted()
                .toList();

        System.out.println("Sorted: " + integers);

        Map<String, List<Transaction>> collect = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getName));

        System.out.println("Grouped transactions by name/category: " + collect);

        //Total price of products by category:
        Map<String, Integer> collect1 = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getName, Collectors.summingInt(Transaction::getAmount)));

        System.out.println("Sum of all transactions by name/category: " + collect1);
    }

}
