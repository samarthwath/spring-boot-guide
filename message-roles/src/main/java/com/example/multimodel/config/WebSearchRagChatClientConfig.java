package com.example.multimodel.config;

import com.example.multimodel.rag.WebSearchDocumentRetriever;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
public class WebSearchRagChatClientConfig {

    @Bean("webSearchRagChatClient")
    public ChatClient webSearchRagChatClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory, RestClient.Builder restClientBuilder) {
        Advisor simpleLoggerAdvisor = new SimpleLoggerAdvisor();
        Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        RetrievalAugmentationAdvisor retrievalAugmentationAdvisor = RetrievalAugmentationAdvisor
                .builder()
                .documentRetriever(WebSearchDocumentRetriever
                        .builder()
                        .maxResults(3)
                        .restClientBuilder(restClientBuilder)
                        .build()
                )
                .build();

        return ChatClient
                .builder(openAiChatModel)
                .defaultAdvisors(List.of(simpleLoggerAdvisor, messageChatMemoryAdvisor, retrievalAugmentationAdvisor))
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
