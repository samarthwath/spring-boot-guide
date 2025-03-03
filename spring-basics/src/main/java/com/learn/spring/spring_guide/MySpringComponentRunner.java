package com.learn.spring.spring_guide;

import com.learn.spring.spring_guide.bean.components.PersonBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.learn.spring.spring_guide.bean.components")
public class MySpringComponentRunner {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(MySpringComponentRunner.class);
        System.out.println("Context initialization completed.");
        PersonBean personBean = applicationContext.getBean(PersonBean.class);
        System.out.println("Logging personBean value: " + personBean);
        personBean.cleanup();
    }
}
