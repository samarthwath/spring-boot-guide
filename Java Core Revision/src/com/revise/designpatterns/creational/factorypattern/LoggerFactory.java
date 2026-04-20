package com.revise.designpatterns.creational.factorypattern;

public class LoggerFactory {

    public static Logger getLogger(String type) {
        switch (type) {
            case "mysql_logger":
                return new MySqlLogger();
            case "console_logger":
                return new ConsoleLogger();
            case "file_logger":
                return new FileLogger();
        }
        return null;
    }
}
