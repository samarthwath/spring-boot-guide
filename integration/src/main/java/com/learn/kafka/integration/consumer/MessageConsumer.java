package com.learn.kafka.integration.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MessageConsumer {

    @KafkaListener(topics = {"fruits"}, groupId = "abc")
    public void consumeMessage(String message) {
        log.info("Message received: " + message);
    }

}
