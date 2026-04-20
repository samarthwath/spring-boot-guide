package com.revise.testconcepts;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsDeloitte {

    public static void main(String[] args) {
        List<String> names = List.of("Samarth", "Ankit", "Amit");
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 101);
        List<String> namesReversed = names
                .stream()
                .map((String string) -> {
                    StringBuffer buffer = new StringBuffer(string);
                    String reversedString = buffer.reverse().toString();
                    return reversedString;
                })
                .toList();
        System.out.println("List with reversed names:  " + namesReversed);


        names
                .stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        List<Integer> integers = nums
                .stream()
                .filter(num -> num % 2 == 0)
                .toList();
        System.out.println("Even numbers: " + integers);

        List<Integer> integers1 = nums
                .stream()
                .filter(num -> num % 2 == 0)
                .filter(num -> num > 2)
                .map(num -> num * num)
                .toList();
        System.out.println("Squared Even numbers with greater than 2: " + integers1);

        Optional<Integer> max = nums
                .stream()
                .max(Comparator.naturalOrder());
        System.out.println(max.orElse(0));

        Optional<Integer> first = nums
                .stream()
                .sorted(Comparator.reverseOrder())
                .findFirst();
        System.out.println(first.orElse(0));

        Optional<Integer> first1 = nums
                .stream()
                .sorted(Comparator.naturalOrder())
                .findFirst();
        System.out.println(first1.orElse(0));

        Optional<Integer> min = nums
                .stream()
                .min((n1, n2) -> {
                    return n1.compareTo(n2);
                });
        System.out.println(min.orElse(0));


        List<Employee> employees = Arrays.asList(
                new Employee("Sunny", 20000, "IT"),
                new Employee("Samarth", 20000, "IT"),
                new Employee("Pushpak", 200000, "Manufacturing"),
                new Employee("Demo", 30000, "IT"),
                new Employee("Test", 540000, "Manufacturing"),
                new Employee("Dummy", 76000, "Hospitality")
        );
        System.out.println("Employees: " + employees);
        List<Employee> list = employees
                .stream()
                .sorted(Comparator
                        .comparing(Employee::getName)
                        .thenComparing(Employee::getSalary)
                        .thenComparing(Employee::getDepartment)
                )
                .toList();

        System.out.println("Employees: " + list);

        Map<String, Long> departmentWithEmployeesCount = employees
                .stream()
                .collect(Collectors
                        .groupingBy(Employee::getDepartment, Collectors.counting())
                );

        System.out.println("Department with Employees count: " + departmentWithEmployeesCount);

        Employee employee = employees
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .findFirst()
                .orElse(null);
        System.out.println(employee);

        //Find all the employees with second largest salary

        Employee employee1 = employees
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println(employee1);
    }
}
