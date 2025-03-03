package com.learn.spring.spring_boot.controller.props;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DemoBean {
    private int id;
    private String message;



}
