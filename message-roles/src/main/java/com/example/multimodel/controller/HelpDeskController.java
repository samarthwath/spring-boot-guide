package com.example.multimodel.controller;

import com.example.multimodel.tools.HelpDeskTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tools")
public class HelpDeskController {

    private final ChatClient chatClient;

    private final HelpDeskTools helpDeskTools;

    public HelpDeskController(@Qualifier("helpDeskChatClient") ChatClient chatClient, HelpDeskTools helpDeskTools) {
        this.chatClient = chatClient;
        this.helpDeskTools = helpDeskTools;
    }

    @GetMapping("/help-desk")
    public ResponseEntity<?> interactWithHelpDesk(@RequestHeader String username, @RequestParam String message) {
        String response = chatClient
                .prompt()
                .tools(helpDeskTools)
                .toolContext(Map.of("username", username))
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok(response);
    }

}
