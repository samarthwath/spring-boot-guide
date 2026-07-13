package com.example.multimodel.controller;

import com.example.multimodel.model.CountryCities;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/structured-output")
public class StructuredOutputController {

    private final ChatClient chatClient;

    public StructuredOutputController(@Qualifier("openaiNewChatClient") ChatClient openaiNewChatClient) {
        this.chatClient = openaiNewChatClient;
    }

    @GetMapping("sample-output")
    public ResponseEntity<CountryCities> getStructuredOutput(@RequestParam String question) {
        CountryCities countryCitiesEntity = chatClient
                .prompt()
                .user(question)
                .call()
                .entity(CountryCities.class);
        return ResponseEntity.ok().body(countryCitiesEntity);
    }

    @GetMapping("sample-list")
    public ResponseEntity<List<String>> getStructuredList(@RequestParam String question) {
        List<String> cities = chatClient
                .prompt()
                .user(question)
                .call()
                .entity(new ListOutputConverter());
        return ResponseEntity.ok().body(cities);
    }

    @GetMapping("sample-map")
    public ResponseEntity<Map<String, Object>> getStructuredMap(@RequestParam String question) {
        Map<String, Object> citiesInfo = chatClient
                .prompt()
                .user(question)
                .call()
                .entity(new MapOutputConverter());
        return ResponseEntity.ok().body(citiesInfo);
    }

    @GetMapping("sample-bean")
    public ResponseEntity<CountryCities> getStructuredBean(@RequestParam String question) {
        CountryCities countryCitiesEntity = chatClient
                .prompt()
                .user(question)
                .call()
                .entity(new BeanOutputConverter<CountryCities>(CountryCities.class));
        return ResponseEntity.ok().body(countryCitiesEntity);
    }

    @GetMapping("custom-bean-list")
    public ResponseEntity<List<CountryCities>> getStructuredBeanList(@RequestParam String question) {
        List<CountryCities> countryCitiesList = chatClient
                .prompt()
                .user(question)
                .call()
                .entity(new ParameterizedTypeReference<List<CountryCities>>() {
                });
        return ResponseEntity.ok().body(countryCitiesList);
    }

}
