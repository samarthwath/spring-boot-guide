package com.javafeatures;

import java.util.Optional;

public class OptionalClassExample {
    public static void main(String[] args) {
        //Legacy way of handling null values.
        //Without optional.
        String userNameById = OptionalClassExample.getUserNameById(125);
        if (userNameById != null) {
            System.out.println(userNameById);
        }

        //With optional implementation:
        Optional<String> userNameByIdOptional = OptionalClassExample.getUserNameByIdOptionalImplementation(123);
        userNameByIdOptional.ifPresentOrElse(
                username -> {
                    System.out.println("Username found: " + username);
                }, () -> {
                    System.out.println("Username not found with the given id.");
                });
    }


    public static String getUserNameById(int userId) {
        if (userId == 0) {
            return null;
        } else {
            return "Samarth";
        }
    }

    public static Optional<String> getUserNameByIdOptionalImplementation(int userId) {
        if (userId == 0) {
            return Optional.empty();
        } else {
            return Optional.ofNullable("Samarth");
        }
    }
}
