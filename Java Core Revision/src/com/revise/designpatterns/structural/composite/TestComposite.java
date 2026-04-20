package com.revise.designpatterns.structural.composite;

public class TestComposite {
    public static void main(String[] args) {
        MyFile fileFirst = new MyFile("test.txt");
        MyFile fileSecond = new MyFile("demo.txt");
        MyFile fileThird = new MyFile("readme.md");

        MyFolder documents = new MyFolder("Documents");
        documents.addFile(fileFirst);
        documents.addFile(fileSecond);

        MyFolder root = new MyFolder("Root");
        root.addFile(fileThird);
        root.addFile(documents);


        root.showDetails();


    }


}
