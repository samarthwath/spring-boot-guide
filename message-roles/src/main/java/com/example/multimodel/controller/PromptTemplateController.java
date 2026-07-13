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
@RequestMapping("/api/prompt-template")
public class PromptTemplateController {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/userPrompt.st")
    private Resource userPromptTemplate;

    public PromptTemplateController(@Qualifier("openaiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    //String promptTemplate = "A customer named {customerName} sent the following message: {customerMessage}. Write a polite and helpful email response addressing the issue. Maintain a professional tone and provide reassurance. Respond as if you're writing the email body only. Don't include subject, signature";

    @GetMapping("/email")
    public ResponseEntity<?> emailResponse(@RequestParam String customerName, @RequestParam String customerMessage) {
        String returnedContent = chatClient
                .prompt()
                .system("You are a professional customer service assistant which helps drafting email responses to improve the productivity of the customer support team.")
                .user(promptTemplateSpec -> promptTemplateSpec
                        .text(userPromptTemplate)
                        .param("customerName", customerName)
                        .param("customerMessage", customerMessage)
                )
                .call()
                .content();
        return ResponseEntity.ok().body("Hello " + returnedContent);
    }

}
