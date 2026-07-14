package com.embarkx.blogapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApiApplication {

	private static final Logger log = LoggerFactory.getLogger(BlogApiApplication.class);

	public static void main(String[] args) {
		log.info("Starting BlogApiApplication");
		SpringApplication.run(BlogApiApplication.class, args);
	}

}
