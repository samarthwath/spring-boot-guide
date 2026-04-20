package com.learn.kafka.integration.controller;

import com.learn.kafka.integration.service.RedisService;
import com.learn.kafka.integration.utils.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    @GetMapping("send-message")
    public ResponseEntity<String> sendToRedis(@RequestParam String message) {
        redisTemplate.opsForValue().set("email", message);
        Object email = redisTemplate.opsForValue().get("email");
        log.info("Redis cached key email with value : " + email.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = restTemplate
                .exchange("https://69116caa7686c0e9c20d5746.mockapi.io/dummy-api", HttpMethod.GET, httpEntity, String.class);
        log.info("exchange.getBody() : " + exchange.getBody());
        return ResponseEntity.ok("Message pushed to redis");
    }


    //Traditional way of caching
    @GetMapping("weather")
    public ResponseEntity getCityWeather(@RequestParam String city) {
        String cachedResponse = redisService.get("weather_of_" + city);
        if (cachedResponse != null) {
            log.info("cachedResponse : " + cachedResponse);
            return ResponseEntity.ok(cachedResponse);
        } else {
            String weatherThroughAPI = fetchWeatherThroughAPI(city);
            log.info("weatherThroughAPI : " + weatherThroughAPI);
            redisService.set("weather_of_" + city, weatherThroughAPI, 300l);
            return ResponseEntity.ok(weatherThroughAPI);
        }
    }

    public String fetchWeatherThroughAPI(String city) {
        StringBuffer uri = new StringBuffer("https://69116caa7686c0e9c20d5746.mockapi.io/dummy-api");
        uri.append("?city=").append(city);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> exchange = restTemplate
                .exchange(uri.toString(), HttpMethod.GET, httpEntity, String.class);
        log.info("exchange.getBody() city weather: " + exchange.getBody());
        return exchange.getBody();
    }

    @GetMapping("weather-info")
    public ResponseEntity getWeatherDetails(@RequestParam String city) {
        return ResponseEntity.ok(redisService.fetchWeatherDetails(city));
    }

    @DeleteMapping("weather-info")
    public ResponseEntity<String> deleteWeatherDetails(@RequestParam String city) {
        redisService.clearCache(city);
        return ResponseEntity.ok("Cache cleared for city: " + city);
    }


}
