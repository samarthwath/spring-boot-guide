package com.example.multimodel.config;

import com.example.multimodel.advisors.TokeUsageAuditorAdvisor;
import com.example.multimodel.rag.PIIMaskingDocumentPostProcessor;
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
public class ChatClientConfig {

    @Bean
    public ChatClient openaiChatClient(OpenAiChatModel openAiChatModel) {
        OpenAiChatOptions.Builder chatOptionsBuilder = OpenAiChatOptions
                .builder()
                .model("gpt-5.4-mini")
                .temperature(0.8)
                .maxCompletionTokens(10);
        return ChatClient
                .builder(openAiChatModel)
                .defaultOptions(chatOptionsBuilder)
                //.defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokeUsageAuditorAdvisor()))
                .defaultSystem("You are an internal HR assistant. Your role is to help employees with questions related to HR policies such as leave policies, working hours, benefits and code of conduct. If a use ask for help anything outside of these topics, kindly inform them that you can only assists with queries related to HR policies.")
                .build();
    }

    @Bean
    public ChatClient openaiNewChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient
                .builder(openAiChatModel)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }


    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory
                .builder()
                .maxMessages(10)
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .build();
    }

    @Bean
    public ChatClient openaiChatMemoryClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory, RetrievalAugmentationAdvisor retrievalAugmentationAdvisor, SemanticCacheAdvisor semanticCacheAdvisor) {
        Advisor simpleLoggerAdvisor = new SimpleLoggerAdvisor();
        Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient
                .builder(openAiChatModel)
                .defaultAdvisors(List.of(simpleLoggerAdvisor, messageChatMemoryAdvisor, retrievalAugmentationAdvisor, semanticCacheAdvisor))
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }


    /*@Bean("chatMemoryChatClient")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory
            ,RetrievalAugmentationAdvisor  retrievalAugmentationAdvisor) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor tokenUsageAdvisor = new TokeUsageAuditorAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return chatClientBuilder
                .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor,tokenUsageAdvisor,
                        retrievalAugmentationAdvisor))
                .build();
    }*/

    @Bean
    public RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(VectorStore vectorStore, @Qualifier("chatClientBuilder") ChatClient.Builder chatClientBuilder) {
        return RetrievalAugmentationAdvisor
                .builder()
                .queryTransformers(TranslationQueryTransformer.builder().chatClientBuilder(chatClientBuilder.clone()).targetLanguage("en").build())
                .documentRetriever(
                        VectorStoreDocumentRetriever
                                .builder()
                                .vectorStore(vectorStore)
                                .topK(3)
                                .similarityThreshold(0.5)
                                .build()
                )
               .documentPostProcessors(PIIMaskingDocumentPostProcessor.builder())
        .build();
    }

    @Bean("chatClientBuilder")
    public ChatClient.Builder getChatClientBuilder(OpenAiChatModel openAiChatModel) {
        return ChatClient
                .builder(openAiChatModel);
    }
}
