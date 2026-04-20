package com.learn.kafka.integration.utils;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private String name;
    private String city;
    private String county;
    private String id;

}
