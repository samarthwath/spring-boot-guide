package com.javafeatures;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> userNameById = findUserNameById(123);
        Optional<String> userNameByIdSecond = findUserNameById(0);
        userNameById.ifPresentOrElse((userName) -> {
                    System.out.println("Use name is present: " + userName);
                },
                () -> {
                    System.out.println("User name is not present for the given id");
                }
        );

        userNameByIdSecond.ifPresentOrElse((userName) -> {
            System.out.println("User name is present: " + userName);
        }, () -> {
            System.out.println("User name is not present for the given id");
        });
    }

    private static Optional<String> findUserNameById(int i) {
        if (i == 0) {
            return Optional.empty();
        } else {
            return Optional.ofNullable("John");
        }
    }
}
