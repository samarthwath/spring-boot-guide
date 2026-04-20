package com.revise.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class MyFolder implements FileSystemComponent {

    private List<FileSystemComponent> components = new ArrayList<>();

    private String name;

    public MyFolder(String name) {
        this.name = name;
    }

    public void addFile(FileSystemComponent component) {
        components.add(component);
    }


    @Override
    public void showDetails() {
        System.out.println("Folder " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}
