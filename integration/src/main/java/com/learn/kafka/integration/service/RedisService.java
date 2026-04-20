package com.learn.kafka.integration.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private RestTemplate restTemplate;


    public String get(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o == null ? null : o.toString();
    }

    public void set(String key, Object value, Long expireTime) {
        redisTemplate.opsForValue().set(key, value.toString(), expireTime, TimeUnit.SECONDS);
    }

    @Cacheable(value = "weatherinfo", key = "#city")
    public String fetchWeatherDetails(String city) {
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

    @CacheEvict(value = "weatherinfo", key = "#city")
    public void clearCache(String city) {
        log.info("Cache cleared for city: {}", city);
    }
}
