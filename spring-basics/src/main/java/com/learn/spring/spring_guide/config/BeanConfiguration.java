package com.learn.spring.spring_guide.config;

import com.learn.spring.spring_guide.bean.Address;
import com.learn.spring.spring_guide.bean.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfiguration {

    @Bean
    @Primary
    public Address address() {
        return new Address(452010, "Indore", "India");
    }

    @Bean
    @Qualifier("addressSecond")
    public Address addressSecond() {
        return new Address(453555, "Ujjain", "India");
    }

    //Autowiring using method call.
    @Bean
    @Primary
    public Person person() {
        return new Person(1, "Samarth", address());
    }

    //Autowiring with method parameters.
    @Bean
    public Person personSecond(@Qualifier("addressSecond") Address addressSecond) {
        return new Person(2, "Pushpak", addressSecond);
    }

}
