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
public class MessageRolesController {

    private final ChatClient openaiChatClient;

    //private final ChatClient ollamaChatClient;

    public MessageRolesController(@Qualifier("openaiChatClient") ChatClient openaiChatClient) {
        this.openaiChatClient = openaiChatClient;
        //this.ollamaChatClient = ollamaChatClient;

    }

    @GetMapping("/openai-chat")
    public ResponseEntity<?> openaiChat(@RequestParam String message) {
        //String returnedContent = openaiChatClient.prompt(message).call().content();
        String returnedContent = openaiChatClient
                .prompt()
                //Not required to use system as we have passed it in default
                //Need to pass only if we want to override system role for LLM message
                //.system("You are an internal HR assistant. Your role is to help employees with questions related to HR policies such as leave policies, working hours, benefits and code of conduct. If a use ask for help anything outside of these topics, kindly inform them that you can only assists with queries related to HR policies.")
                .user(message)
                .call()
                .content();
        return ResponseEntity.ok().body("Hello " + returnedContent);
    }

}
