package com.learn.spring.learn.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello from sayHello";
    }

    /**
     * We have added tomcat-embed-jasper dependency in order to run jsp on tomcat.
     *
     * @return
     */
    @RequestMapping("/say-hello-jsp")
    public String sayHelloHtml() {
        return "sayHello";
    }
}
