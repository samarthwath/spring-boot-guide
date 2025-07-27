package com.learn.kafka.producer.controller;

import com.learn.kafka.producer.event.OrderEvent;
import com.learn.kafka.producer.produce.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderEvent orderEvent) {
        orderProducer.sendOrder(orderEvent);
        return ResponseEntity.ok("Order Placed!!");
    }
}
