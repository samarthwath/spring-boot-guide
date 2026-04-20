package com.revise.testconcepts;

import java.util.*;
import java.util.stream.Collectors;

public class StreamConcept {

    public static void main(String[] args) {
        List<String> names = List.of("sam", "john", "alex");
        System.out.println("names: " + names);
        List<String> upperCasedStrings = names
                .stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("upperCasedNames: " + upperCasedStrings);

        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 101);
        List<Integer> evenNumbers = nums
                .stream()
                .filter(num -> num % 2 == 0)
                .toList();
        System.out.println("evenNumbers: " + evenNumbers);

        long count = nums
                .stream()
                .filter(num -> num > 10)
                .count();

        System.out.println("Number Greater than 10: " + count);

        Optional<Integer> first = nums
                .stream()
                .filter(num -> num > 5)
                .findFirst();

        Integer firstNumGreaterThanFive = first.orElse(0);
        System.out.println("firstNumberGreaterThanFive: " + firstNumGreaterThanFive);

        boolean b = nums
                .stream()
                .allMatch(num -> num >= 0);

        System.out.println("allMatch: " + b);


        List<String> list = names
                .stream()
                .sorted(Comparator.naturalOrder())
                .toList();

        System.out.println("sorted names: " + list);

        List<String> descendingList = names
                .stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        System.out.println("descendingList: " + descendingList);

        List<Integer> duplicates = List.of(1, 1, 2, 3, 4, 5, 6, 6, 6);
        List<Integer> removedDuplicates = duplicates
                .stream()
                .distinct()
                .toList();

        System.out.println("RemovedDuplicates: " + removedDuplicates);

        int sum = duplicates
                .stream()
                .distinct()
                .filter(num -> num % 2 == 0)
                .mapToInt(num -> num * num)
                .sum();

        System.out.println("Sum of squares of distinct event numbers: " + sum);


        Optional<Integer> maximumNumber = nums
                .stream()
                .max(Comparator.naturalOrder());

        Integer maximum = maximumNumber.orElse(0);
        System.out.println("Maximum: " + maximum);

        Optional<Integer> min = nums
                .stream()
                .min((num1, num2) -> {
                    return num1.compareTo(num2);
                });

        Integer minimumNumber = min.orElse(-1);
        System.out.println("Minimum Number: " + minimumNumber);

        Integer minimum = nums
                .stream()
                .min(Comparator.naturalOrder())
                .orElse(-1);
        System.out.println("Minimum: " + minimum);

        String str = "hello";
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (Character character : str.toCharArray()) {
            if (frequencyMap.containsKey(character)) {
                Integer i = frequencyMap.get(character);
                i += 1;
                frequencyMap.put(character, i);
            } else {
                frequencyMap.put(character, 1);
            }
        }
        System.out.println("frequencyMap: " + frequencyMap);


        List<Employee> employees = Arrays.asList(
                new Employee("Sunny", 20000, "IT"),
                new Employee("Samarth", 20000, "IT"),
                new Employee("Pushpak", 200000, "Manufacturing"),
                new Employee("Demo", 30000, "IT"),
                new Employee("Test", 540000, "Manufacturing"),
                new Employee("Dummy", 76000, "Hospitality")
        );

        System.out.println("Employee before sorting: " + employees);
        employees.sort(Comparator.comparing(Employee::getSalary));
        System.out.println("Employee after sorting: ");
        System.out.println(employees);

        employees.sort(Comparator
                .comparing(Employee::getSalary)
                .thenComparing(Employee::getName)
                .thenComparing(Employee::getDepartment)
        );
        System.out.println("Employee after sorting by Salary first and then by department: ");
        System.out.println(employees);

        //Group Employees by Department:

        Map<String, List<Employee>> employeesGroupedByDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("Employees grouped by Department: ");
        System.out.println(employeesGroupedByDepartment);

        Map<String, Long> collect = employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.counting()
                ));

        System.out.println("Count Employees by Department: " + collect);

        Optional<Employee> maxSalariedEmployee = employees
                .stream()
                .max(Comparator.comparingInt(Employee::getSalary));

        Employee employee = maxSalariedEmployee.orElse(null);
        System.out.println("Max Salaried Employee: " + employee);

        Optional<Employee> minimumSalariedEmployee = employees
                .stream()
                .min(Comparator.comparingInt(Employee::getSalary));

        Employee minSalariedEmployee = minimumSalariedEmployee
                .orElse(null);
        System.out.println("Min Salaried Employee: " + minSalariedEmployee);


        Optional<Employee> maximumSalariedEmployeeNew = employees
                .stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .findFirst();
        System.out.println("Maximum Salaried Employee New: " + maximumSalariedEmployeeNew.orElse(null));


        //String sorting according to length:
        List<String> sortedStringAccordingToLength = names
                .stream()
                .sorted(Comparator
                        .comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder())
                )
                .toList();
        System.out.println("sortedStringAccordingToLength: " + sortedStringAccordingToLength);

        Optional<Employee> secondLargestSalariedEmployee = employees
                .stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .skip(1)
                .findFirst();

        Employee employeeSecondLargestSalary = secondLargestSalariedEmployee
                .orElse(null);

        System.out.println("Employee secondLargestSalary: " + employeeSecondLargestSalary);

        //Find Top 2 Employees with salary greater than 30000
        List<Employee> topTwoEmployees = employees
                .stream()
                .filter(nemployee -> nemployee.getSalary() > 30000)
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(3)
                .toList();

        System.out.println("topTwoEmployees: " + topTwoEmployees);


        List<Employee> newEmployees = Arrays.asList(
                new Employee("Sunny", 20000, "IT"),
                new Employee("Samarth", 20000, "IT"),
                new Employee("Pushpak", 200000, "Manufacturing"),
                new Employee("Demo", 30000, "IT"),
                new Employee("Test", 540000, "Manufacturing"),
                new Employee("Dummy", 76000, "Hospitality"),
                new Employee("Null Employee", 0, null),
                new Employee("Null Second Employee", 0, null)
        );


        //Get UpperCased names of all the employees with department null
        List<String> upperCasedEmployeeNamesSorted = newEmployees
                .stream()
                .filter(employee1 -> employee1.getDepartment() == null)
                .map(employee1 -> employee1.getName().toUpperCase())
                .sorted(Comparator.comparing(String::toUpperCase))
                .toList();

        System.out.println("upperCasedEmployeeNamesSorted: " + upperCasedEmployeeNamesSorted);

        List<String> sortedNames = names
                .stream()
                .sorted((str1, str2) -> {
                    return str1.compareTo(str2);
                })
                .toList();

        List<String> newSortedNames = names
                .stream()
                .sorted(Comparator.comparing(str::compareTo))
                .toList();

        System.out.println("newSortedNames: "+newSortedNames);
        System.out.println("sortedNames: " + sortedNames);

    }


}
