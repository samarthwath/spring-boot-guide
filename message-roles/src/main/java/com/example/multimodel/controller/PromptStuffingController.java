package com.example.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prompt-stuffing")
public class PromptStuffingController {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    private Resource systemPromptTemplate;

    public PromptStuffingController(@Qualifier("openaiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping("/example")
    public ResponseEntity<?> promptStuffing(@RequestParam String message) {
        String returnedContent = chatClient
                .prompt()
                .system(promptSystemSpec -> promptSystemSpec.text(systemPromptTemplate))
                .user(message)
                .call()
                .content();
        return ResponseEntity.ok().body("Hello " + returnedContent);
    }


}
