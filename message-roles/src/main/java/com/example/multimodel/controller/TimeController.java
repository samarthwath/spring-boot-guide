package com.example.multimodel.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tools")
public class TimeController {

    private final ChatClient chatClient;

    public TimeController(@Qualifier("timeChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/real-time")
    public ResponseEntity<?> timeChatClient(@RequestHeader String username, @RequestParam String message) {
        String llmResponseContent = chatClient
                .prompt()
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                .call()
                .content();

        return ResponseEntity.ok(llmResponseContent);
    }
}
