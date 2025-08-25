package com.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) {
        Person person = new Person("Samarth Wath", 25);
        System.out.println("Person Name before reflection: " + person.getName());
        System.out.println("Person age before reflection: " + person.getAge());
        try {
            Field nameField = Person.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(person, "Hello Samarth Wath");
            System.out.println("Person name after reflection: " + person.getName());
            Field ageField = Person.class.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.set(person, 26);
            System.out.println("Person age after reflection: " + person.getAge());
            Method nameAndAgeMethod = Person.class.getDeclaredMethod("nameAndAge");
            nameAndAgeMethod.setAccessible(true);
            nameAndAgeMethod.invoke(person);
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
