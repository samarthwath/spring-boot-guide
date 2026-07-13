package com.example.multimodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HrRagController {

    private final ChatClient chatClient;

    private final VectorStore vectorStore;

    private final ChatClient webSearchRagChatClient;

    @Value("classpath:/promptTemplates/hrPromptTemplate.st")
    Resource hrPromptTemplate;


    public HrRagController(@Qualifier("openaiChatMemoryClient") ChatClient chatClient, VectorStore vectorStore, @Qualifier("webSearchRagChatClient") ChatClient webSearchRagChatClient) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
        this.webSearchRagChatClient = webSearchRagChatClient;
    }

    @GetMapping("/hr-assistant")
    public ResponseEntity<?> hrAssistant(@RequestHeader String username, @RequestParam String message) {
        /*SearchRequest searchRequest = SearchRequest.builder().query(message).topK(3).similarityThreshold(0.5).build();
        List<Document> documents = vectorStore.similaritySearch(searchRequest);
        String similarContext = documents
                .stream()
                .map(Document::getText)
                .collect(Collectors.joining(System.lineSeparator()));*/

        String responseString = chatClient
                .prompt()
                //.system(promptSystemSpec -> promptSystemSpec.text(hrPromptTemplate).param("documents", similarContext))
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok(responseString);
    }

    @GetMapping("/web-search-rag")
    public ResponseEntity<?> webSearchRagChat(@RequestHeader String username, @RequestParam String message) {
        /*SearchRequest searchRequest = SearchRequest.builder().query(message).topK(3).similarityThreshold(0.5).build();
        List<Document> documents = vectorStore.similaritySearch(searchRequest);
        String similarContext = documents
                .stream()
                .map(Document::getText)
                .collect(Collectors.joining(System.lineSeparator()));*/

        String responseString = webSearchRagChatClient
                .prompt()
                //.system(promptSystemSpec -> promptSystemSpec.text(hrPromptTemplate).param("documents", similarContext))
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok(responseString);
    }


}
