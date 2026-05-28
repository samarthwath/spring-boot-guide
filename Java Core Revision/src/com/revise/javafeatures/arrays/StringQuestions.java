package com.revise.javafeatures.arrays;

import java.util.*;
import java.util.stream.*;

public class StringQuestions {
    public static void main(String[] args) {
        //Remove outermost parentheses:
        String parentheses = "()(()())(())";
        int level = 0;
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < parentheses.length(); i++) {
            if (parentheses.charAt(i) == '(') {
                if (level > 0) {
                    result.append(parentheses.charAt(i));
                    level++;
                }
            } else if (parentheses.charAt(i) == ')') {
                level--;
                if (level > 0) {
                    result.append(parentheses.charAt(i));
                }
            }
        }
        System.out.println("String after removing outermost parentheses: " + result.toString());
        String myString = "Welcome to the jungle";
        String[] splittedString = myString.split(" ");
        System.out.println("String before reversal: " + myString);
        //Reverse the string:
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = splittedString.length - 1; i >= 0; i--) {
            stringBuffer.append(splittedString[i] + " ");
        }
        System.out.println("String after reversal: " + stringBuffer.toString());


        String newString = "Hi There welcome to    the jungle";
        List<String> stringWithoutSpaces = new ArrayList<>();
        StringBuffer stringCreator = new StringBuffer();
        StringBuffer stringReversed = new StringBuffer();
        for (int i = 0; i < newString.length(); i++) {
            if (newString.charAt(i) != ' ') {
                stringCreator.append(newString.charAt(i));
                if (i == newString.length() - 1) {
                    stringWithoutSpaces.add(stringCreator.toString());
                }
            } else if (newString.charAt(i) == ' ') {
                if (!stringCreator.isEmpty()) {
                    stringWithoutSpaces.add(stringCreator.toString());
                }
                stringCreator = new StringBuffer();
            }
        }
        System.out.println(stringWithoutSpaces);
        for (int i = stringWithoutSpaces.size() - 1; i >= 0; i--) {
            stringReversed.append(stringWithoutSpaces.get(i) + " ");
        }
        System.out.println("String after reversal brute force approach: " + stringReversed.toString());

        String palindrome = "abab";
        boolean isPalindrome = true;
        for (int i = 0; i < palindrome.length() / 2; i++) {
            if (palindrome.charAt(i) != palindrome.charAt(palindrome.length() - i - 1)) {
                isPalindrome = false;
            }
        }
        if (isPalindrome) {
            System.out.println(palindrome + " " + " is palindrome");
        } else {
            System.out.println(palindrome + " " + " is not a palindrome");
        }
        String[] strings = {"flower", "flow", "flight"};
        Arrays.sort(strings);
        String firstString = strings[0];
        String lastString = strings[strings.length - 1];
        StringBuffer longestCommonPrefix = new StringBuffer();
        for (int i = 0; i < firstString.length(); i++) {
            if (firstString.charAt(i) == lastString.charAt(i)) {
                longestCommonPrefix.append(firstString.charAt(i));
            }
        }
        System.out.println("Longest common prefix: " + longestCommonPrefix.toString());

        String s1 = "paper";
        String s2 = "title";
        boolean areStringIsomorphic = true;
        Map<Character, Character> isomporphicMap = new HashMap<>();
        if (s1.length() != s2.length()) {
            areStringIsomorphic = false;
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (isomporphicMap.containsKey(s1.charAt(i)) && !(isomporphicMap.get(s1.charAt(i)) == s2.charAt(i))) {
                    areStringIsomorphic = false;
                } else {
                    isomporphicMap.put(s1.charAt(i), s2.charAt(i));
                }
            }
        }
        System.out.println("Are two strings isomorphic: " + areStringIsomorphic);

        //Check if one string is rotation of another:
        String myNewString = "rotation";
        //rotation
        //otationr
        //tationro
        //ationrot
        //tionrota
        String goal = "tionrota";
//        myNewString="hello";
//        goal="llohe";
        StringBuffer stringEval = new StringBuffer(myNewString);
        boolean isStringRotationOfAnotherString = false;
        for (int i = 0; i < stringEval.length(); i++) {
            //String replacedString = myNewString.replace(myNewString.charAt(myNewString.length() - 1), myNewString.charAt(0));
            Character addCharacter = stringEval.charAt(0);
            stringEval.append(addCharacter);
            stringEval.deleteCharAt(0);
            //System.out.println(stringEval.toString());
            if (stringEval.toString().equals(goal)) {
                isStringRotationOfAnotherString = true;
                break;
            }
        }
        System.out.println("Is string rotation of another my approach: " + isStringRotationOfAnotherString);

        String n1 = "hello";
        String n2 = "llohe";
        boolean isStringRotationOfAnother = false;
        for (int i = 0; i < n1.length(); i++) {
            String concatenatedString = n1.substring(i) + n1.substring(0, i);
            if (concatenatedString.equals(n2)) {
                isStringRotationOfAnother = true;
            }
        }
        System.out.println("Is string rotation of another easy brute force: " + isStringRotationOfAnother);

        String anagramS1 = "silent";
        String anagramS2 = "listen";
        boolean areStringsAnagrams = true;
        char[] anagramCharacterS1 = anagramS1.toCharArray();
        char[] anagramCharacterS2 = anagramS2.toCharArray();
        //Check if two strings are anagram of each other:
        Arrays.sort(anagramCharacterS1);
        Arrays.sort(anagramCharacterS2);
        for (int i = 0; i < anagramCharacterS1.length; i++) {
            if (anagramCharacterS1[i] != anagramCharacterS2[i]) {
                areStringsAnagrams = false;
            }
        }
        System.out.println("Strings: " + anagramS1 + " : " + anagramS2);
        System.out.println("Are stringsAnagrams brute force approach: " + areStringsAnagrams);

        Map<Character, Integer> characterOccurencesMap = new HashMap<>();
        boolean areStringsAnagram = true;
        for (int i = 0; i < anagramCharacterS1.length; i++) {
            if (characterOccurencesMap.containsKey(anagramCharacterS1[i])) {
                characterOccurencesMap.put(anagramCharacterS1[i], characterOccurencesMap.get(anagramCharacterS1[i]) + 1);
            } else {
                characterOccurencesMap.put(anagramCharacterS1[i], 1);
            }
        }
        for (int i = 0; i < anagramCharacterS2.length; i++) {
            if (characterOccurencesMap.containsKey(anagramCharacterS2[i])) {
                characterOccurencesMap.put(anagramCharacterS2[i], characterOccurencesMap.get(anagramCharacterS2[i]) - 1);
            }
        }
        for (Character key : characterOccurencesMap.keySet()) {
            if (characterOccurencesMap.get(key) != 0) {
                areStringsAnagram = false;
            }
        }
        System.out.println("Are strings anagram optimal approach: " + areStringsAnagram);

        //sort characters by frequency:
        String charFrequency = "raaaajj";
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (int i = 0; i < charFrequency.length(); i++) {
            if (charFrequencyMap.containsKey(charFrequency.charAt(i))) {
                charFrequencyMap.put(charFrequency.charAt(i), charFrequencyMap.get(charFrequency.charAt(i)) + 1);
            } else {
                charFrequencyMap.put(charFrequency.charAt(i), 1);
            }
        }
        List<Character> sortedCharactersAccordingToFrequency = charFrequencyMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(mapEntry -> mapEntry.getKey(), mapEntry -> mapEntry.getValue(), (oldValue, newValue) -> newValue, () -> new LinkedHashMap<Character, Integer>()))
                .entrySet()
                .stream()
                .map(extractKey -> extractKey.getKey())
                .toList();

        System.out.println("Character frequency map: " + charFrequencyMap);
        System.out.println("Sorted characters according to frequency map: " + sortedCharactersAccordingToFrequency);
    }
}
