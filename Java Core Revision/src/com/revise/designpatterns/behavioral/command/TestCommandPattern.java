package com.revise.designpatterns.behavioral.command;


//Client
public class TestCommandPattern {

    public static void main(String[] args) {
        FileOperationExecutor fileOperationExecutor = new FileOperationExecutor();
        fileOperationExecutor
                .executeOperation(new CloseFileOperation(new TextFile("abc.txt")), "abc.txt");

        fileOperationExecutor
                .executeOperation(new OpenFileOperation(new TextFile("demo.txt")), "demo.txt");

        fileOperationExecutor
                .executeOperation(new SaveFileOperation(new TextFile("xyz.txt")), "xyz.txt");

        fileOperationExecutor.listOperationsExecuted();

    }


}
