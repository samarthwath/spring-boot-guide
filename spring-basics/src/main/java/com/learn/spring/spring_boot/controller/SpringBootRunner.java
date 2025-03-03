package com.learn.spring.spring_boot.controller;

import com.learn.spring.spring_boot.controller.props.DemoBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRunner.class);
        DemoBean bean = context.getBean(DemoBean.class);
        System.out.println("Logging object hashCode of demoBean: " + bean.hashCode());
    }
}
