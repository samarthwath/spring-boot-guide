package com.revise.designpatterns.creational.builder;

public class TestBuilderDesignPattern {
    public static void main(String[] args) {
        User builtUser = User
                .UserBuilder
                .builder()
                .setUsername("samarthwath")
                .setEmailId("samarthwath11@ggmail.com")
                .setAddress("Indore")
                .build();

        System.out.println("Log User object from builder: " + builtUser);


    }

}
