package com.revise.designpatterns.behavioral.command;
//Concrete Class
public class SaveFileOperation implements FileOperation {
    private TextFile textFile;

    public SaveFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public String toString() {
        return "SaveFileOperation{" +
                "textFile=" + textFile +
                '}';
    }

    @Override
    public void execute(String fileName) {
        textFile.saveFile();
    }
}
