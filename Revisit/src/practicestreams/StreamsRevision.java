package practicestreams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsRevision {

    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(1, 3, 2, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> collectedEvenElements = integerStream.filter(intValue -> intValue % 2 == 0).collect(Collectors.toList());
        System.out.println("Log collectedEvenElementsList: " + collectedEvenElements);
        Stream<Person> personStream = Stream.of(new Person(1, "Samarth Wath", "Indore"));
        System.out.println("Logging personStream: " + personStream);
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "Ram", "Bhopal"));
        personList.add(new Person(2, "Shyam", "Indore"));
        List<Integer> numsList = new ArrayList();
        numsList.add(12);
        numsList.add(45);
        numsList.add(12);
        numsList.add(100);
        numsList.add(55);
        numsList.add(33);
        System.out.println("Log numsList: " + numsList);
        List<Integer> distinctElementsList = numsList.stream().distinct().collect(Collectors.toList());
        System.out.println("Log distinctElementsList: " + distinctElementsList);
        long count = numsList.stream().count();
        System.out.println("Log count of numsList elements: " + count);
        long distinctElementsCount = numsList.stream().distinct().count();
        System.out.println("Log distinctElementsCount: " + distinctElementsCount);
        Map<Integer, Long> collect = numsList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Log collectedElements: " + collect);
        List<Integer> collectedSortedList = numsList.stream().sorted().collect(Collectors.toList());
        System.out.println("Log collectedSortedList: " + collectedSortedList);
        List<Integer> reverseSortedList = numsList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("Log reverseSortedList: " + reverseSortedList);
        //Print the numbers which are multiple of 5.
        List<Integer> listMultipleOfFive = numsList.stream().filter(number -> number % 5 == 0).collect(Collectors.toList());
        System.out.println("Logging list with multiple of 5" + listMultipleOfFive);
        Optional<Integer> max = numsList.stream().max(Comparator.naturalOrder());
        int maxValue = max.isEmpty() ? max.get() : 0;
        System.out.println("Logging maxValue: " + maxValue);
        List<Integer> minimumTopThreeElementsList = numsList.stream().sorted().limit(3).collect(Collectors.toList());
        System.out.println("Minimum top three elements of the list: " + minimumTopThreeElementsList);
        List<Integer> maximumTopThreeElementsList = numsList.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
        System.out.println("Maximum top three elements of the list: " + maximumTopThreeElementsList);
        Optional<Integer> sumObtained = numsList.stream().reduce((numFirst, numSecond) -> numFirst + numSecond);
        int sumValue = sumObtained.isEmpty() ? 0 : sumObtained.get();
        System.out.println("Log sumValue: " + sumValue);
        Optional<Integer> secondMax = numsList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst();
        int secondMaxValue = secondMax.isEmpty() ? 0 : secondMax.get();
        System.out.println("Log secondMaxValue: " + secondMaxValue);
        Optional<Integer> secondSmallest = numsList.stream().sorted(Comparator.naturalOrder()).skip(1).findFirst();
        int secondSmallestValue = secondSmallest.isEmpty() ? 0 : secondSmallest.get();
        System.out.println("Log secondSmallestValue: " + secondSmallestValue);
        String originalString = "Java Concept Of The Day.";
        String[] splittedString = originalString.split(" ");
        Arrays.stream(splittedString).map(stringValue -> new StringBuffer(stringValue).reverse()).forEach(reversedStringValue -> System.out.println(reversedStringValue));
        String stringCheck = "ROTATOR";
        String[] split = stringCheck.split("");
        Arrays.stream(split).forEach(splitCharacter -> System.out.println(splitCharacter));
        List<Person> personListNew = new ArrayList();
        personListNew.add(new Person(1, "Samarth Wath", "Indore"));
        personListNew.add(new Person(2, "Pushpak Wath", "Bhopal"));
        personListNew.add(new Person(3, "Amit Wath", "Ujjain"));
        personListNew.add(new Person(4, "Ankit Wath", "Delhi"));
        personListNew.add(new Person(5, "Kush Wath", "Pune"));
        personListNew.add(new Person(6, "Lub", "Bangalore"));
        List<Person> sortedPersonList = personListNew.stream().sorted(Comparator.comparing(person -> {
            System.out.println("Log personName" + person.getName());
            return person.getName();
        })).collect(Collectors.toList());
        System.out.println("Log sortedPersonList: ");
        System.out.println(sortedPersonList);
        List<Person> sortedPersonListReverseOrder = personListNew.stream().sorted(Comparator.comparing((Person person) -> {
            return person.getName();
        }).reversed()).collect(Collectors.toList());
        System.out.println("Sorted Person List in reverse order: ");
        System.out.println(sortedPersonListReverseOrder);
        List<Person> personSortedWithNameAndCity = personListNew.stream().sorted(Comparator.comparing((Person person) -> {
            return person.getName();
        }).thenComparing((Person person) -> {
            return person.getId();
        })).collect(Collectors.toList());
        System.out.println("Person sorted List first with name and then with city: ");
        System.out.println(personSortedWithNameAndCity);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Indore", "Samarth", "Wath"));
        employeeList.add(new Employee(2, "Pune", "Pushpak", "Wath"));
        employeeList.add(new Employee(3, "Sausar", "Shantaram", "Wath"));
        employeeList.add(new Employee(4, "Sausar", "Minakshi", "Wath"));
        System.out.println("Employee list before sorting: ");
        System.out.println(employeeList);
        Comparator<Employee> employeeComparator = (employeeFirst, employeeSecond) -> {
            System.out.println("Employee first firstName: " + employeeFirst.getFirstName());
            System.out.println("Employee second firstName: " + employeeSecond.getFirstName());
            int compareValue = employeeFirst.getFirstName().compareTo(employeeSecond.getFirstName());
            System.out.println("Log compareValue: " + compareValue);
            return compareValue;
        };
        employeeList.sort(employeeComparator);
        System.out.println("Employee list after sorting: ");
        System.out.println(employeeList);
        Comparator<Employee> employeeComparatorReverse = (employeeFirst, employeeSecond) -> {
            return -employeeFirst.getFirstName().compareTo(employeeSecond.getFirstName());
        };
        employeeList.sort(employeeComparatorReverse);
        System.out.println("Employee list after sorting in reverse order: ");
        System.out.println(employeeList);
        StreamsRevision.myGet(2);
    }

    public static void myGet(Object object) {
        System.out.println("Inside myGet object: " + object);
    }

    public static void myGet(String string) {
        System.out.println("Inside myGet of string: " + string);
    }
}
