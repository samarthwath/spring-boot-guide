package com.strings;

public final class PersonImmutable {

    private final int age;
    private final String name;

    public PersonImmutable(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
