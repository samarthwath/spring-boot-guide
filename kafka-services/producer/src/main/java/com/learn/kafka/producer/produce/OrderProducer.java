package com.learn.kafka.producer.produce;

import com.learn.kafka.producer.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrder(OrderEvent orderEvent) {
        kafkaTemplate.send("order.placed", orderEvent);
    }
}
