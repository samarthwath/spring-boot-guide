package com.generics;

public class TestWithoutGenerics {

    public static void main(String[] args) {
        WithoutGenerics withoutGenerics = new WithoutGenerics();
        withoutGenerics.setItem(1);

        //Since no generics is used below line will give runtime error.
        //there should be compile type check for this.
        withoutGenerics.setItem("Hello");
        int item = (int) withoutGenerics.getItem();
        System.out.println("Log item value: " + item);
    }
}
