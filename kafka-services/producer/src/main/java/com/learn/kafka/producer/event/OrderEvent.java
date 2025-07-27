package com.learn.kafka.producer.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEvent {
    private int orderId;
    private String product;
    private int quantity;
}
