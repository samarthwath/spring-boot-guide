package com.learn.kafka.integration.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CircuitBreakerService {

    @Autowired
    private RestTemplate restTemplate;

    private static int counter = 0;

    private static int retryCounter = 0;

    @CircuitBreaker(name = "weatherapi", fallbackMethod = "weatherFallback")
    public String getDataFromExternalApi(String city) throws Exception {
        counter++;
        log.info("Inside getDataFromExternalApi, counter={}", counter);

        if (counter <= 5) {
            StringBuffer url = new StringBuffer("https://69116caa7686c0e9c20d5746.mockapi.io/dummy-api");
            url.append("?city=").append(city);

            HttpHeaders headers = new HttpHeaders();
            headers.set("content-type", "application/json");
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

            ResponseEntity<String> exchange =
                    restTemplate.exchange(url.toString(), HttpMethod.GET, httpEntity, String.class);

            String responseBody = exchange.getBody();
            log.info("responseBody: {}", responseBody);
            return responseBody;
        } else {
            log.info("weatherapi failed: {}", counter);
            throw new RuntimeException("weatherapi not responding");
        }
    }

    @Retry(name = "weatherapiretry", fallbackMethod = "retryWeatherFallback")
    public String getDataFromExternalApiRetry(String city) throws Exception {
        retryCounter++;
        log.info("Inside getDataFromExternalApiRetry, counter={}", retryCounter);

        if (retryCounter <= 5) {
            StringBuffer url = new StringBuffer("https://69116caa7686c0e9c20d5746.mockapi.io/dummy-api");
            url.append("?city=").append(city);

            HttpHeaders headers = new HttpHeaders();
            headers.set("content-type", "application/json");
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

            ResponseEntity<String> exchange =
                    restTemplate.exchange(url.toString(), HttpMethod.GET, httpEntity, String.class);

            String responseBody = exchange.getBody();
            log.info("responseBody new: {}", responseBody);
            return responseBody;
        } else {
            log.info("weatherapi failed new: {}", retryCounter);
            throw new RuntimeException("weatherapi not responding new");
        }
    }


    public String weatherFallback(String city, Exception ex) {
        log.info("weatherFallback called for city: {}, error: {}", city, ex.getMessage());
        return "weatherFallback";
    }

    public void clearCounter() {
        counter = 0;
        retryCounter = 0;
    }

    public String retryWeatherFallback(String city, Exception ex) {
        log.info("Inside retryWeatherFallback called for city: {}, error: {}", city, ex.getMessage());
        return "retryWeatherFallback";
    }
}