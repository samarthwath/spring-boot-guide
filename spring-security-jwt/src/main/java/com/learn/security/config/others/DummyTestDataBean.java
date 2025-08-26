package com.learn.security.config.others;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class DummyTestDataBean {


    @Value("${server.port}")
    private String serverPort;

    public String testConfiguration(){
        return serverPort;
    }
}
