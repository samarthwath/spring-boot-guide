package com.example.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MultiModelChatController {

    private final ChatClient openaiChatClient;

    private final ChatClient ollamaChatClient;

    public MultiModelChatController(@Qualifier("openaiChatClient") ChatClient openaiChatClient, @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openaiChatClient = openaiChatClient;
        this.ollamaChatClient = ollamaChatClient;

    }

    @GetMapping("/openai-chat")
    public ResponseEntity<?> openaiChat(@RequestParam String message) {
        String returnedContent = openaiChatClient.prompt(message).call().content();
        return ResponseEntity.ok().body("Hello " + returnedContent);
    }

    @GetMapping("/ollama-chat")
    public ResponseEntity<?> ollamaChat(@RequestParam String message) {
        String returnedContent = ollamaChatClient.prompt(message).call().content();
        return ResponseEntity.ok().body("Hello " + returnedContent);
    }
}
