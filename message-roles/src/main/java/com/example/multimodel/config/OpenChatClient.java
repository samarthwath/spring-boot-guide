package com.example.multimodel.config;

import com.example.multimodel.advisors.TokeUsageAuditorAdvisor;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenChatClient {

    @Bean("openEndedChatClient")
    public ChatClient openEndedChatClient(OpenAiChatModel openAiChatModel, SemanticCacheAdvisor semanticCacheAdvisor) {
        return
                ChatClient
                        .builder(openAiChatModel)
                        .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokeUsageAuditorAdvisor(), semanticCacheAdvisor))
                        .build();
    }

    @Bean("qdrantOpenEndedChatClient")
    public ChatClient qdrantOpenEndedChatClient(OpenAiChatModel openAiChatModel, @Qualifier("qdrantSemanticCacheAdvisor") SemanticCacheAdvisor semanticCacheAdvisor) {
        return
                ChatClient
                        .builder(openAiChatModel)
                        .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokeUsageAuditorAdvisor(), semanticCacheAdvisor))
                        .build();
    }
}
