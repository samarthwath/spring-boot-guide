package com.example.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/stream")
public class StreamController {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    private Resource systemPromptTemplate;

    public StreamController(@Qualifier("openaiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /*@GetMapping("/example")
    public Flux<String> streamExample(@RequestParam String message) {
        return chatClient
                .prompt()
                .system(promptSystemSpec -> promptSystemSpec.text(systemPromptTemplate))
                .user(message)
                .stream()
                .content();
    }*/

}
