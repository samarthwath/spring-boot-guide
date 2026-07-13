package com.example.multimodel.config;

import com.example.multimodel.advisors.TokeUsageAuditorAdvisor;
import com.example.multimodel.rag.PIIMaskingDocumentPostProcessor;
import com.example.multimodel.tools.TimeTools;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TimeChatClientConfig {


    @Bean("timeChatClient")
    public ChatClient openaiChatMemoryClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory, TimeTools timeTools) {
        Advisor simpleLoggerAdvisor = new SimpleLoggerAdvisor();
        Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient
                .builder(openAiChatModel)
                .defaultTools(timeTools)
                .defaultAdvisors(List.of(simpleLoggerAdvisor, messageChatMemoryAdvisor))
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }


}
