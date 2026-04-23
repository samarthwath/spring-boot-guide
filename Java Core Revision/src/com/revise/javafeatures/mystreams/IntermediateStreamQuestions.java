package com.revise.javafeatures.mystreams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IntermediateStreamQuestions {
    public static void main(String[] args) {
        String str = "ammarrtthhs";
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> duplicateNumbers = List.of(9, 9, 2, 1, 1, 23, 23);
        List<Integer> newDuplicates = List.of(1, 2, 34, 4, 34, 45, 45);
        List<Integer> evenNumbers = List.of(2, 4, 6, 8, 10);
        List<String> strings = List.of("abc", "def", "ijk", "lmnopqrstuvwxyz");

        //Find first non repeating character in string brute force
        for (int index = 0; index < str.length(); index++) {
            boolean firstNonRepeating = false;
            for (int internalIndex = 0; internalIndex < str.length(); internalIndex++) {
                if (index != internalIndex && str.charAt(index) == str.charAt(internalIndex)) {
                    firstNonRepeating = true;
                    break;
                }
            }
            if (!firstNonRepeating) {
                System.out.println("firstNonRepeating: " + str.charAt(index));
                break;
            }
        }

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int index = 0; index < str.length(); index++) {
            map.put(str.charAt(index), map.getOrDefault(str.charAt(index), 0) + 1);
        }

        //2. Frequency of each character brute force
        System.out.println("map: " + map);

        //3. //Find first non repeating character in string better approach
        for (Character key : map.keySet()) {
            if (map.get(key) == 1) {
                System.out.println("First nonRepeating: " + key);
                break;
            }
        }

        Character c1 = str
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(ch -> ch, () -> new LinkedHashMap<Character, Long>(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);
        System.out.println(c1);

        //Frequency of each character
        Map<Character, Long> frequencyOfEachCharsMap = str
                .chars()
                .mapToObj(character -> (char) character)
                .collect(Collectors.groupingBy(
                        ch -> ch,
                        Collectors.counting()
                ));
        System.out.println(frequencyOfEachCharsMap);

        //Second Highest number in a list.
        Integer secondHighest = numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(-1);
        System.out.println(secondHighest);

        //Second highest number from duplicate list:
        Integer secondHighestFromDuplicates = duplicateNumbers
                .stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(-1);
        System.out.println(secondHighestFromDuplicates);

        Integer maxElement = numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(-1);
        System.out.println(maxElement);

        Integer minimumElement = numbers
                .stream()
                .sorted()
                .findFirst()
                .orElse(-1);
        System.out.println(minimumElement);

        //Any duplicate element:
        Set<Integer> integers = new HashSet<>();
        Set<Integer> allDuplicates = new HashSet<>();
        boolean b = duplicateNumbers
                .stream()
                .anyMatch(number -> {
                    return !integers.add(number);
                });
        System.out.println(b);

        //List contains all even numbers:
        boolean allMatch = evenNumbers
                .stream()
                .allMatch(number -> number % 2 == 0);
        System.out.println(allMatch);

        //Find first duplicate:
        Set<Integer> elementsSet = new LinkedHashSet<>();
        Integer firstDuplicateElement = newDuplicates
                .stream()
                .filter(element -> !elementsSet.add(element))
                .findFirst()
                .orElse(-1);
        System.out.println(firstDuplicateElement);

        Set<Integer> countSet = new LinkedHashSet<>();
        //Count duplicates:
        long duplicatesCount = newDuplicates
                .stream()
                .filter(element -> !countSet.add(element))
                .count();
        System.out.println(duplicatesCount);

        //Remove duplicates while preserving order:
        Set<Integer> removeDuplicates = new LinkedHashSet<>();
        List<Integer> integers1 = newDuplicates
                .stream()
                .filter(element -> removeDuplicates.add(element))
                .toList();

        System.out.println(integers1);

        //Find top 3 highest numbers:
        List<Integer> topThreeHighestNumbers = numbers
                .stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .sorted()
                .toList();
        System.out.println(topThreeHighestNumbers);

        //Longest string in a list:
        String longestString = strings
                .stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .findFirst()
                .orElse(null);
        System.out.println(longestString);

        //Partition numbers into even and odd:
        Map<Boolean, List<Integer>> partitionedList = numbers
                .stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0));
        System.out.println(partitionedList);

        //Group Employees by Department:
        List<Employee> employees = List.of(
                new Employee("Samarth", 123456, "IT"),
                new Employee("Test", 2344, "INFRA"),
                new Employee("Abhishek", 56789, "IT"),
                new Employee("Kishan", 987787, "IT")
        );
        Map<String, List<Employee>> collect = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(collect);

        //Employee with max salary
        Employee employeeWithMaxSalary = employees
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
        System.out.println(employeeWithMaxSalary);

        //Average salary per department
        Map<String, Double> averageSalaryPerDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

        System.out.println(averageSalaryPerDepartment);

        //Group words according to there length
        Map<Integer, List<String>> groupWordsLengthWise = strings
                .stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(groupWordsLengthWise);

        //Highest Paid employee in each department
        Map<String, Optional<Employee>> highestPaidEmployeeDepartmentWise = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println(highestPaidEmployeeDepartmentWise);


        List<Transaction> transactions = List.of(
                new Transaction("Samarth", 1234, "Grocery"),
                new Transaction("Samarth", 7777, "Miscellaneous"),
                new Transaction("Badri", 78987, "Gadgets"),
                new Transaction("Badri", 9898, "Adhoc")
        );
        //From list of Transaction find total amount per user
        Map<String, Integer> totalAmountPerUser = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getUser, Collectors.summingInt(Transaction::getAmount)));

        System.out.println(totalAmountPerUser);

        //Sort product by price and then by rating
        List<Product> products = List.of(
                new Product("12345", 4000, "5"),
                new Product("12346", 8000, "3"),
                new Product("12347", 9000, "4.5"),
                new Product("12348", 4000, "5")
        );

        List<Product> sortedProductListPriceRating = products
                .stream()
                .sorted(Comparator.comparing(Product::getPrice)
                        .thenComparing(Product::getRating)
                )
                .toList();

        System.out.println(sortedProductListPriceRating);

        //Find all numbers starting with 1
        List<Integer> allNumbersStartsWithOne = numbers
                .stream()
                .map(num -> String.valueOf(num))
                .filter(element -> element.startsWith("1"))
                .map(element -> Integer.parseInt(element))
                .toList();
        System.out.println(allNumbersStartsWithOne);

    }

}
