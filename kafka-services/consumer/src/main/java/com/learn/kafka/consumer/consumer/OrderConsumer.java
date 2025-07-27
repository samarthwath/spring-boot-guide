package com.learn.kafka.consumer.consumer;

import com.learn.kafka.consumer.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "order.placed", groupId = "inventory-group")
    public void consumer(OrderEvent orderEvent) {
        logger.info("Order Consumer for order id: {}", orderEvent.toString());
    }
}
