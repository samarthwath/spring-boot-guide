package com.learn.security.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Scheduling {

    private static final Logger logger = LoggerFactory.getLogger(Scheduling.class);

    @Scheduled(fixedDelay = 1000)
    public void fixedDelay() throws InterruptedException {
        Thread.sleep(3000);
        logger.info("fixedDelay thread: time:{}, {}", Thread.currentThread().getName(),  System.currentTimeMillis()/1000);
    }

    @Scheduled(fixedRate = 1000)
    @Async
    public void fixedRate() throws InterruptedException {
        Thread.sleep(3000);
        logger.info("fixedRate thread: time:{}, {}", Thread.currentThread().getName(),  System.currentTimeMillis()/1000);
    }
}
