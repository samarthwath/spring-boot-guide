package com.revise.javafeatures.mystreams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsRecall {

    public static void main(String[] args) {
        String str = "ammarrtthhs";
        List<Integer> duplicateNumbers = List.of(9, 9, 2, 1, 1, 23, 23);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> strings = List.of("abc", "abc", "def", "def", "ijk", "lmnopqrstuvwxyz");

        Map<String, String> stringStringMap = Map.ofEntries(Map.entry("hi", "there"));
        System.out.println(stringStringMap);

        //Find first non repeating character using streams:
        Map.Entry<Character, Long> characterLongEntry = str
                .chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        ch -> ch,
                        () -> new LinkedHashMap<>(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .findFirst()
                .orElse(null);

        System.out.println(characterLongEntry);

        //Frequency of each character:
        str
                .chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        character -> character,
                        Collectors.counting()
                ))
                .entrySet()
                .forEach(characterEntry -> System.out.println(characterEntry.getKey() + "=" + characterEntry.getValue()));

        //Second highest number from duplicates list
        Integer secondHighestNumber = duplicateNumbers
                .stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(-1);
        System.out.println(secondHighestNumber);

        Integer maxElement = numbers
                .stream()
                .max(Comparator.naturalOrder())
                .orElse(-1);
        System.out.println(maxElement);

        Integer minElement = numbers
                .stream()
                .min(Comparator.naturalOrder())
                .orElse(-1);
        System.out.println(minElement);

        //Does list contains any duplicate element
        Set<Integer> set = new HashSet<>();
        boolean isDuplicatePresent = duplicateNumbers
                .stream()
                .anyMatch(number -> !set.add(number));
        System.out.println(isDuplicatePresent);

        //Does list contains all duplicate elements:
        Set<Integer> numsSet = new HashSet<>();
        boolean areAllDuplicateElements = duplicateNumbers
                .stream()
                .allMatch(number -> !numsSet.add(number));

        System.out.println(areAllDuplicateElements);


        //Find any duplicate element from the list
        Set<Integer> numberSet = new HashSet();
        Integer anyDuplicateElement = duplicateNumbers
                .stream()
                .filter(number -> !numberSet.add(number))
                .findFirst()
                .orElse(99999999);
        System.out.println(anyDuplicateElement);

        //Count Duplicate elements:
        Set<Integer> duplicateSet = new HashSet<>();
        long duplicatesCount = duplicateNumbers
                .stream()
                .filter(number -> !duplicateSet.add(number))
                .count();
        System.out.println(duplicatesCount);

        //Remove duplicates while preserving order:
        Set<Integer> nonDuplicates = new HashSet<>();
        List<Integer> listAfterDuplicatesRemoval = duplicateNumbers
                .stream()
                .filter(number -> nonDuplicates.add(number))
                .toList();
        System.out.println(listAfterDuplicatesRemoval);

        //Find top 3 highest numbers:
        List<Integer> topThreeHighestNumbers = numbers
                .stream()
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
                .orElse("");
        System.out.println(longestString);

        //Partition numbers into even and odd:
        Map<Boolean, List<Integer>> partitionedListInEvenOdd = numbers
                .stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0));
        System.out.println(partitionedListInEvenOdd);

        List<Employee> employees = List.of(
                new Employee("Samarth", 123456, "IT"),
                new Employee("Test", 2344, "INFRA"),
                new Employee("Abhishek", 56789, "IT"),
                new Employee("Kishan", 987787, "IT")
        );
        //Group Employees by department:

        Map<String, List<Employee>> groupEmployeesByDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(groupEmployeesByDepartment);

        //Employee with Max salary:
        Employee maxSalariedEmployee = employees
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
        System.out.println(maxSalariedEmployee);

        Employee maxSalariedWaalaEmployee = employees
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .findFirst()
                .orElse(null);
        System.out.println(maxSalariedWaalaEmployee);

        //Employee with min salary:
        Employee minimumSalariedEmployee = employees
                .stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
        System.out.println(minimumSalariedEmployee);

        Employee minSalariedEmployee = employees
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .findFirst()
                .orElse(null);
        System.out.println(minSalariedEmployee);

        //Average salary of employee per department:
        Map<String, Double> averageSalaryEmployeeDepartmentWise = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println(averageSalaryEmployeeDepartmentWise);

        //Group words according to there length:
        Map<String, Long> collect = strings
                .stream()
                .collect(Collectors.groupingBy(string -> string,
                        Collectors.counting()
                ));
        System.out.println(collect);



        //Highest paid employee in each department:
        Map<String, Optional<Employee>> highestPaidEmployeeDepartmentWise = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))
                ));
        System.out.println(highestPaidEmployeeDepartmentWise);

        List<Transaction> transactions = List.of(
                new Transaction("Samarth", 1234, "Grocery"),
                new Transaction("Samarth", 7777, "Miscellaneous"),
                new Transaction("Badri", 78987, "Gadgets"),
                new Transaction("Badri", 9898, "Adhoc")
        );

        //From list of transaction find total amount per user
        Map<String, Integer> totalAmountPerUser = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getUser,
                        Collectors.summingInt(Transaction::getAmount)
                ));
        System.out.println(totalAmountPerUser);

        List<Product> products = List.of(
                new Product("12345", 4000, "5"),
                new Product("12346", 8000, "3"),
                new Product("12347", 9000, "4.5"),
                new Product("12348", 4000, "6")
        );

        //Sort products by price and then by rating:
        List<Product> sortProductsByPriceAndThenRating = products
                .stream()
                .sorted(Comparator.comparing(Product::getPrice)
                        .thenComparing(Product::getRating)
                )
                .toList();
        System.out.println(sortProductsByPriceAndThenRating);

        //Find all numbers starting with 1
        List<String> allNumbersStartingWithOne = numbers
                .stream()
                .map(numValue -> String.valueOf(numValue))
                .filter(numberString -> numberString.startsWith("1"))
                .toList();
        System.out.println(allNumbersStartingWithOne);
    }
}
