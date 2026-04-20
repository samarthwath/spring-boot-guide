package com.revise.designpatterns.structural.composite;

public class MyFile implements FileSystemComponent {
    private String name;

    public MyFile(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}
