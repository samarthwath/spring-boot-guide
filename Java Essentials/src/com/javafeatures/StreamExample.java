package com.javafeatures;

import java.util.*;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);

        Optional<Integer> reducedResult = integerList
                .stream()
                .filter(element -> element % 2 == 0)
                .map(element -> element * element)
                .reduce((elementFirst, elementSecond) -> elementFirst + elementSecond);

        Integer sumOfSquaresOfEvenNumbers = reducedResult.orElse(0);
        System.out.println("Sum of squares of even numbers: " + sumOfSquaresOfEvenNumbers);

        //map vs flatmap:
        List<List<Integer>> integerListSecond = new ArrayList<>();
        integerListSecond.add(Arrays.asList(1, 2, 3, 4, 5));
        integerListSecond.add(Arrays.asList(6, 7, 8, 9, 10));

        System.out.println("Log integerListSecond: ");
        System.out.println(integerListSecond);

        List<Integer> collectedList = integerListSecond.stream()
                .flatMap(list -> list.stream())
                .map(element -> element * element)
                .collect(Collectors.toList());

        System.out.println("Collected list: " + collectedList);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Grocery", 1000));
        transactions.add(new Transaction("Grocery", 500));
        transactions.add(new Transaction("Entertainment", 2000));
        transactions.add(new Transaction("Entertainment", 1000));
        transactions.add(new Transaction("Miscellaneous", 5000));

        //Total expense:
        Integer totalExpense = transactions
                .stream()
                .mapToInt(transaction -> transaction.getAmount())
                .reduce((t1Value, t2Value) -> t1Value + t2Value)
                .orElse(0);
        System.out.println("Total Expense: " + totalExpense);

        System.out.println("Total items in transactions list: " + transactions.size());

        //Calculate the total amount spent on grocery.
        Optional<Integer> grocerySum = transactions
                .stream()
                .filter(transaction -> transaction.getCategory().equals("Grocery"))
                .map(transaction -> transaction.getAmount())
                .reduce((t1, t2) -> t1 + t2);
        grocerySum.ifPresent(sumValue -> System.out.println("Grocery sum: " + sumValue));


        //average expense across all categories.
        OptionalDouble average = transactions
                .stream()
                .mapToDouble(transaction -> transaction.getAmount())
                .average();

        double averageOfAllCategories = average.orElse(0.0);
        System.out.println("Average of all categories: " + averageOfAllCategories);


        //square of even numbers from duplicate list:
        List<Integer> integerDuplicateList = Arrays.asList(1, 1, 2, 3, 2, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7, 8, 9, 9);

        System.out.println("Log integerDuplicateList: ");
        System.out.println(integerDuplicateList);

        List<Integer> distinctSquareOfEvenNumbers = integerDuplicateList
                .stream()
                .filter(element -> element % 2 == 0)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Distinct square of even numbers: ");
        System.out.println(distinctSquareOfEvenNumbers);

        //Count of Numbers which are greater than 5.
        long countOfElements = integerDuplicateList
                .stream()
                .filter(element -> element > 5)
                .count();
        System.out.println("Count of elements which are greater than 5 in integerDuplicateList: ");
        System.out.println(countOfElements);


        //sum of all the distinct numbers:
        int sum = integerDuplicateList
                .stream()
                .distinct()
                .mapToInt(element -> element.intValue())
                .sum();

        System.out.println("Sum of distinct elements: " + sum);


        //find even numbers and sort them.
        List<Integer> intList = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
        System.out.println("intList: ");
        System.out.println(intList);
        List<Integer> sortedEvenNumbersIntegerList = intList
                .stream()
                .filter(element -> element % 2 == 0)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted even numbers integer list: ");
        System.out.println(sortedEvenNumbersIntegerList);

        //sort list of string in alphabetical order.
        List<String> strings = Arrays.asList("ankit", "kumar", "sanu", "suresh", "kishan");
        System.out.println("String list before sorting: ");
        System.out.println(strings);
        strings.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("String list after sorting: ");
        System.out.println(strings);

        List<String> sortedStringList = strings
                .stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted string list: ");
        System.out.println(sortedStringList);


    }
}
