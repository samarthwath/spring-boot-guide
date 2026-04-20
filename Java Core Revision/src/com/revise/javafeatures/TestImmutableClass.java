package com.revise.javafeatures;

public class TestImmutableClass {
    public static void main(String[] args) {
        ImmutableClass immutableClass = new ImmutableClass("samarthwath", "12345");
        System.out.println(immutableClass.hashCode());
    }

}
