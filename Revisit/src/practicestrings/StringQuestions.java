package practicestrings;

import java.util.HashMap;
import java.util.Set;

public class StringQuestions {
    public static void main(String[] args) {
        String str = "rotator";
        System.out.println("Is string palindrome: " + checkPalindrome(str));
        HashMap<Character, Integer> characterIntegerHashMap = countOccurencesOfCharacters(str);
        System.out.println("Log characterIntegerHashMap: " + characterIntegerHashMap);
//        checkAnagramsStrings("Bored", "Robed");
        System.out.println(checkAnagramsStrings("bored", "robed"));
    }

    private static boolean checkAnagramsStrings(String stringFirst, String stringSecond) {
        HashMap<Character, Integer> firstStringMap = new HashMap<>();
        HashMap<Character, Integer> secondStringMap = new HashMap<>();
        boolean areStringsAnagrams = false;
        if (stringFirst.length() == stringSecond.length()) {
            for (Character character : stringFirst.toCharArray()) {
                if (firstStringMap.containsKey(character)) {
                    firstStringMap.put(character, firstStringMap.get(character) + 1);
                } else {
                    firstStringMap.put(character, 1);
                }
            }
            for (Character character : stringSecond.toCharArray()) {
                if (secondStringMap.containsKey(character)) {
                    secondStringMap.put(character, secondStringMap.get(character) + 1);
                } else {
                    secondStringMap.put(character, 1);
                }
            }
            System.out.println("Log firstStringMap: " + firstStringMap);
            System.out.println("Log secondStringMap: " + secondStringMap);

            Set<Character> keys = firstStringMap.keySet();
            for (Character characterKey : keys) {
                if (secondStringMap.containsKey(characterKey) && firstStringMap.get(characterKey) == secondStringMap.get(characterKey)) {
                    areStringsAnagrams = true;
                } else {
                    areStringsAnagrams = false;
                    break;
                }
            }
            return areStringsAnagrams;
        }
        return false;
    }

    private static HashMap<Character, Integer> countOccurencesOfCharacters(String str) {
        HashMap<Character, Integer> occurencesMap = new HashMap<>();
        for (Character stringChar : str.toCharArray()) {
            if (occurencesMap.containsKey(stringChar)) {
                occurencesMap.put(stringChar, occurencesMap.get(stringChar) + 1);
            } else {
                occurencesMap.put(stringChar, 1);
            }
        }
        return occurencesMap;
    }

    private static boolean checkPalindrome(String str) {
        StringBuffer reversedStringBuffer = new StringBuffer();
        for (int index = str.length() - 1; index >= 0; index--) {
            reversedStringBuffer.append(str.charAt(index));
        }
        String reversedString = reversedStringBuffer.toString();
        System.out.println("Log reversedString: " + reversedString);
        return str.equals(reversedString);
    }
}
