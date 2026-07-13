package com.example.multimodel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rag")
public class RagController {
    private static final Logger log = LoggerFactory.getLogger(RagController.class);
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/promptTemplates/systemPromptRandomDataTemplate.st")
    Resource promptTemplate;



    public RagController(@Qualifier("openaiChatMemoryClient") ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/random-chat")
    public ResponseEntity<?> randomChat(@RequestHeader String username, @RequestParam String message) {
        SearchRequest searchRequest = SearchRequest.builder().query(message).topK(3).similarityThreshold(0.5).build();
        List<Document> documents = vectorStore.similaritySearch(searchRequest);
        String similarContext = documents
                .stream()
                .map(Document::getText)
                .collect(Collectors.joining(System.lineSeparator()));

        String responseString = chatClient
                .prompt()
                .system(promptSystemSpec -> promptSystemSpec.text(promptTemplate).param("documents", similarContext))
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok(responseString);
    }


}
