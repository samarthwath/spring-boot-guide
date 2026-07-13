package com.example.multimodel.controller;

import org.apache.coyote.Response;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatMemoryController {

    private ChatClient chatClient;

    public ChatMemoryController(@Qualifier("openaiChatMemoryClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping("/chat-memory")
    public ResponseEntity<?> chatWithOpenAi(@RequestHeader("username") String username,  @RequestParam String message) {
        String assistantMessage = chatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                .user(message)
                .call()
                .content();
        return ResponseEntity.ok().body(assistantMessage);
    }
}
