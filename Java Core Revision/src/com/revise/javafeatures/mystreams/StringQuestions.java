package com.revise.javafeatures.mystreams;

import java.util.*;
import java.util.stream.Collectors;

public class StringQuestions {
    public static void main(String[] args) {
        //Palindrome String problem
        String testString = "madam";
        Integer[] threeNumberArray = {193, 345, 567, 789};
        //Sort the array based on the 2nd digit:
        //sorted arr [345, 567, 789, 193]
        List<Integer> integerList = List.of(3, 5, 7, 9);
        Integer[] integerArray = {0, 1, 3, 4};
        Integer[] intArray = {0, -1, -2, 3, 4};
        Integer[] intSecondArray = {0, -1, -2, 3, 4};
        Map<String, Integer> hashMap = Map.of("hello", 4, "hi", 3, "hey", 2, "geeks", 1);
        boolean isStringPalindrome = false;
        for (int i = 0; i < testString.length() / 2; i++) {
            if (testString.charAt(i) == testString.charAt(testString.length() - i - 1)) {
                isStringPalindrome = true;
            } else {
                isStringPalindrome = false;
                break;
            }
        }
        if (isStringPalindrome) {
            System.out.println(testString + " is Palindrome");
        } else {
            System.out.println(testString + " is not Palindrome");
        }
        StringBuffer stringBuffer = new StringBuffer(testString);
        if (testString.equals(stringBuffer.reverse().toString())) {
            System.out.println(testString + " is Palindrome");
        } else {
            System.out.println(testString + "is not Palindrome");
        }

        int a = 10;
        int b = 20;
        System.out.println("Numbers before swap: " + a + " : " + b);
        //Swap two numbers without using the third variable:
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("Numbers after swap: " + a + " : " + b);

        //Program to check if the string contains the vowels:
        boolean isVowelsPresent = false;
        for (int i = 0; i < testString.length(); i++) {
            if (testString.charAt(i) == 'a' || testString.charAt(i) == 'A' || testString.charAt(i) == 'e' || testString.charAt(i) == 'E' || testString.charAt(i) == 'i' || testString.charAt(i) == 'I' || testString.charAt(i) == 'o' || testString.charAt(i) == 'O' || testString.charAt(i) == 'u' || testString.charAt(i) == 'U') {
                isVowelsPresent = true;
                break;
            }
        }
        if (isVowelsPresent) {
            System.out.println(testString + " is Vowels Present");
        } else {
            System.out.println(testString + " is no Vowels Present");
        }

        //Program to check if the given number is prime number:
        int number = 23;
        boolean isNumberPrimeNumber = true;
        if (number <= 1) {
            isNumberPrimeNumber = false;
        } else {
            for (int initializer = 2; initializer < number / 2; initializer++) {
                if (number % initializer == 0) {
                    isNumberPrimeNumber = false;
                    break;
                }
            }
        }
        System.out.println(isNumberPrimeNumber);

        //Fibonacci series using recursion:
        int seqLength = 5;
        for (int i = 0; i < seqLength; i++) {
            System.out.print(fibonacci(i));
        }
        //List contains only odd numbers:
        boolean listContainsAllOdd = integerList
                .stream()
                .allMatch(num -> num % 2 != 0);
        System.out.println(listContainsAllOdd);
        List<Integer> list = Arrays.asList(intArray);
        System.out.println(list);
        Arrays.sort(intArray);
        List<Integer> sortedArray = Arrays.asList(intArray);
        System.out.println(sortedArray);

        //Factorial of a number:
        int factorialNumber = 5;
        int factorialEvaluation = 1;
        for (int i = 1; i <= factorialNumber; i++) {
            factorialEvaluation = factorialEvaluation * i;
        }
        System.out.println(factorialEvaluation);
        Set<Integer> firstSet = new HashSet<>(Arrays.asList(intArray));
        Set<Integer> secondSet = new HashSet<>(Arrays.asList(intSecondArray));
        boolean isTwoArraysContainsSameElements = true;
        if (firstSet.size() != secondSet.size()) {
            isTwoArraysContainsSameElements = false;
        } else {
            for (Integer element : firstSet) {
                if (!secondSet.contains(element)) {
                    isTwoArraysContainsSameElements = false;
                    break;
                }
            }
        }
        System.out.println(isTwoArraysContainsSameElements);
        System.out.println(hashMap);
        Map<String, Integer> sortedMapByValue = hashMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, () -> new LinkedHashMap<>()));
        System.out.println(sortedMapByValue);

        //Distinct characters of string and there count
        System.out.println(stringCharactersFrequencyEvaluator(testString));

        //Strings are Anagrams of each other:
        String strFirst = "listen";
        String strSecond = "silent";
        boolean areStringAnagrams = false;
        Map<Character, Integer> characterIntegerMapFirst = stringCharactersFrequencyEvaluator(strFirst);
        Map<Character, Integer> characterIntegerMapSecond = stringCharactersFrequencyEvaluator(strSecond);
        if (characterIntegerMapFirst.size() != characterIntegerMapSecond.size()) {
            areStringAnagrams = false;
        } else {
            areStringAnagrams = characterIntegerMapFirst
                    .entrySet()
                    .stream()
                    .allMatch(entry -> characterIntegerMapSecond.containsKey(entry.getKey()) && entry.getValue().equals(characterIntegerMapSecond.get(entry.getKey())));
        }
        System.out.println("String anagrams: " + areStringAnagrams);


        Set<Integer> smallestPositiveSet = new HashSet<>();
        for (Integer integer : integerArray) {
            if (integer > 0) {
                smallestPositiveSet.add(integer);
            }
        }
        int smallestPositive = 1;
        while (smallestPositiveSet.contains(smallestPositive)) {
            smallestPositive++;
        }
        System.out.println(smallestPositive);
        int myNumber = 369;
        int evaluation = (myNumber / 10) % 10;
        System.out.println(evaluation);
        List<Integer> arrayBefore = Arrays.asList(threeNumberArray);
        System.out.println(arrayBefore);
        Arrays.sort(threeNumberArray, Comparator.comparing(mNumber -> (mNumber / 10) % 10));
        List<Integer> arrayAfter = Arrays.asList(threeNumberArray);
        System.out.println(arrayAfter);
    }

    public static int fibonacci(int number) {
        if (number == 0) {
            return 0;
        } else if (number == 1) {
            return 1;
        } else {
            return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }

    public static Map<Character, Integer> stringCharactersFrequencyEvaluator(String string) {
        Map<Character, Integer> distinctCharsMap = new HashMap<>();
        //Distinct characters of string and there count
        for (Character character : string.toCharArray()) {
            if (distinctCharsMap.containsKey(character)) {
                distinctCharsMap.put(character, distinctCharsMap.get(character) + 1);
            } else {
                distinctCharsMap.put(character, 1);
            }
        }
        return distinctCharsMap;
    }
}
