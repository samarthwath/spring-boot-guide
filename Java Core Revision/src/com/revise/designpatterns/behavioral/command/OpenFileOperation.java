package com.revise.designpatterns.behavioral.command;
//Concrete Class
public class OpenFileOperation implements FileOperation {

    private TextFile textFile;

    @Override
    public String toString() {
        return "OpenFileOperation{" +
                "textFile=" + textFile +
                '}';
    }

    public OpenFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public void execute(String fileName) {
        textFile.openFile();
    }
}
