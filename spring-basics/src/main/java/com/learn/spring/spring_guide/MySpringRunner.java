package com.learn.spring.spring_guide;


import com.learn.spring.spring_guide.bean.Address;
import com.learn.spring.spring_guide.bean.Person;
import com.learn.spring.spring_guide.config.BeanConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MySpringRunner {
    public static void main(String[] args) {
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        Person person = (Person) annotationConfigApplicationContext.getBean("person");
        System.out.println("Log personDetails: " + person);
        Person personBean = annotationConfigApplicationContext.getBean(Person.class);
        System.out.println("Log personBean: " + personBean);
        Address addressBean = annotationConfigApplicationContext.getBean(Address.class);
        System.out.println("Log addressBean: " + addressBean);
        Person personSecond = (Person) annotationConfigApplicationContext.getBean("personSecond");
        System.out.println("Log personSecond details: " + personSecond);
    }
}
