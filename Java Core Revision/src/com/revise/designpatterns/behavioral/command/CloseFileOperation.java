package com.revise.designpatterns.behavioral.command;

//Concrete Class
public class CloseFileOperation implements FileOperation {

    @Override
    public String toString() {
        return "CloseFileOperation{" +
                "textFile=" + textFile +
                '}';
    }

    private TextFile textFile;

    public CloseFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }


    @Override
    public void execute(String fileName) {
        textFile.closeFile();
    }
}
