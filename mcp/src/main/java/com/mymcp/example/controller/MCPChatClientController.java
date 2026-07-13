package com.mymcp.example.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mcp")
public class MCPChatClientController {


    private final ChatClient chatClient;

    public MCPChatClientController(@Qualifier("mcpChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("chat")
    public ResponseEntity<String> mcpChatClient(@RequestParam String message, @RequestHeader(value = "username", required = false) String username) {
        String content = chatClient
                .prompt()
                .user(message + " My username is: " + username)
                .call()
                .content();
        return ResponseEntity.ok(content);
    }

}
