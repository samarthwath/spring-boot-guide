package com.revise.generics;

import java.util.Locale;

public class GenericInterfaceImpl implements GenericInterface<String> {
    @Override
    public void processToUpperCase(String value) {
        System.out.println("Upper cased string value: " + value.toUpperCase(Locale.ENGLISH));
    }

    @Override
    public void log(String value) {
        System.out.println("String value: " + value);
    }
}
