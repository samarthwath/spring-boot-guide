package com.learn.kafka.consumer.event;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class OrderEvent {
    private int orderId;
    private String product;
    private int quantity;
}

