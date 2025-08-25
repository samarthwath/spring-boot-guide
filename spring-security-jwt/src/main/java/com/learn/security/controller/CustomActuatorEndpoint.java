package com.learn.security.controller;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom-actuator-endpoint")
public class CustomActuatorEndpoint {

    @ReadOperation
    //Above ReadOperation annotation will be used for GET method calls.
    //WriteOperation annotation will be used for POST method calls. 
    public String returnCustomActuatorEndpointData() {
        return "Actuator endpoint data !!";
    }
}
