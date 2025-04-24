package practiceeighttotwentythree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class JavaAllFeatures {
    public static void main(String[] args) {
        var myString = "Hello";
        System.out.println("Log myString variable value with var reserved keyWord: " + myString);
        var myList = new ArrayList<String>();
        myList.add("Hello");
        myList.add("Geeks");
        System.out.println("Log myList values: " + myList);
        System.out.println("Iterating List: ");
        for (var value : myList) {
            System.out.println("Log value: " + value);
        }
        List<String> person = List.of("Person", "Employee", "Department");
        System.out.println("Log personList: ");
        System.out.println(person);

        List<Employee> employeeList = new ArrayList();
        employeeList.add(new Employee(1, "Samarth", "Indore"));
        employeeList.add(new Employee(2, "Ankit", "Bhopal"));
        employeeList.add(new Employee(3, "Sukhi", "Amritsar"));
        List<Employee> newEmployeeList = new CopyOnWriteArrayList<>();
        newEmployeeList.add(new Employee(1, "Samarth", "Indore"));
        newEmployeeList.add(new Employee(2, "Ankit", "Bhopal"));
        newEmployeeList.add(new Employee(3, "Sukhi", "Amritsar"));
        System.out.println("Log employeeList before removal :");
        System.out.println(employeeList);
        List<Employee> employeeListWithSNamesOnly = employeeList.stream().filter((employee) -> {
            if (!employee.name().startsWith("A")) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        System.out.println("Log employeeList after removal of A: ");
        System.out.println(employeeListWithSNamesOnly);
        System.out.println("Log original newEmployeesList");
        System.out.println(newEmployeeList);
        for (Employee employee : newEmployeeList) {
            if (employee.name().startsWith("A")) {
                newEmployeeList.remove(employee);
            }
        }
        System.out.println("Log modified newEmployeeList: ");
        System.out.println(newEmployeeList);

    }
}
