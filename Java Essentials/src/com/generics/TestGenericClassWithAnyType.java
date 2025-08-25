package com.generics;

public class TestGenericClassWithAnyType {

    public static void main(String[] args) {
        GenericClassWithAnyType<String> genericClassWithAnyType = new GenericClassWithAnyType();
        genericClassWithAnyType.setItem("Hello");

        //Now below line is giving compile time error.
        //genericClassWithAnyType.setItem(12);

        String item = genericClassWithAnyType.getItem();

        System.out.println(item);
    }
}
