package com.learn.security.config.others;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DummyDevDataBean {

    @Value("${server.port}")
    private String serverPort;

    public String devConfiguration(){
        return serverPort;
    }
}
