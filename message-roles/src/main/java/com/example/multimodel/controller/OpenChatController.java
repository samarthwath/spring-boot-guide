package com.example.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OpenChatController {

    private final ChatClient chatClient;

    private final ChatClient qdrantOpenEndedChatClient;

    public OpenChatController(@Qualifier("openEndedChatClient") ChatClient chatClient, @Qualifier("qdrantOpenEndedChatClient") ChatClient qdrantOpenEndedChatClient) {
        this.chatClient = chatClient;
        this.qdrantOpenEndedChatClient = qdrantOpenEndedChatClient;
    }

    @GetMapping("/open-ended-chat")
    public ResponseEntity<String> openEndedChat(@RequestParam String message) {
        String llmResponse = chatClient
                .prompt()
                .user(message)
                .call()
                .content();
        return ResponseEntity.ok(llmResponse);
    }

    @GetMapping("/qdrant-open-ended-chat")
    public ResponseEntity<String> qdrantOpenEndedChat(@RequestParam String message) {
        String llmResponse = qdrantOpenEndedChatClient
                .prompt()
                .user(message)
                .call()
                .content();
        return ResponseEntity.ok(llmResponse);
    }
}
