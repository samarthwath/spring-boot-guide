package com.revise.designpatterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;

//Invoker: Invokes Command design pattern and will execute all the commands
public class FileOperationExecutor {

    private List<FileOperation> fileOperations = new ArrayList();

    public void executeOperation(FileOperation fileOperation, String fiName) {
        fileOperations.add(fileOperation);
        fileOperation.execute(fiName);
    }

    public void listOperationsExecuted(){
        for(FileOperation fileOperation: fileOperations){
            System.out.println("Operation executed: "+fileOperation.toString());

        }
    }


}
