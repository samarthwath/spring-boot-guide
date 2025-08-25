package com.strings;

public class StringInternExample {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello").intern();

        System.out.println(str1 == str2); // Output: true (Same object reference)
        System.out.println(str1 == str3); // Output: true (Same object reference)
    }
}
