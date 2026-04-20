package com.revise.designpatterns.creational.factorypattern;

public class FactoryPatternClient {
    public static void main(String[] args) {
        Logger mysqlLogger = LoggerFactory.getLogger("mysql_logger");
        mysqlLogger.log("select * from Employee");

        Logger consoleLogger = LoggerFactory.getLogger("console_logger");
        consoleLogger.log("---- Console Log -----");

        Logger fileLogger = LoggerFactory.getLogger("file_logger");
        fileLogger.log("---I/O Operation -----");
    }

}
