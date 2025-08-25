package com.strings;

public class TestPersonImmutable {
    public static void main(String[] args) {
        PersonImmutable personImmutable = new PersonImmutable(25, "Samath Wath");
        PersonImmutable personImmutableSecond = new PersonImmutable(25, "Samath Wath");
        System.out.println(personImmutable.hashCode());
        System.out.println(personImmutableSecond.hashCode());
    }
}
