package com.learn.spring.newdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewdemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(NewdemoApplication.class);

	public static void main(String[] args) {
		logger.info("Starting NewdemoApplication");
		SpringApplication.run(NewdemoApplication.class, args);
	}

}
