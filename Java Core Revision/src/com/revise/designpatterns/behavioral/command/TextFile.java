package com.revise.designpatterns.behavioral.command;

//Receiver Class
public class TextFile {
    private String fileName;

    public TextFile(String fileName) {
        this.fileName = fileName;
    }

    public void openFile() {
        System.out.println("Opening file: " + fileName);
    }

    @Override
    public String toString() {
        return "TextFile{" +
                "fileName='" + fileName + '\'' +
                '}';
    }

    public void closeFile() {
        System.out.println("Closing file: " + fileName);
    }

    public void saveFile() {
        System.out.println("Saving file: " + fileName);
    }


}
