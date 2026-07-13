package com.example.multimodel.config;

import com.example.multimodel.tools.TimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
public class HelpDeskChatClientConfig {

    @Value("classpath:/promptTemplates/helpDeskSystemPromptTemplate.st")
    private Resource helpDeskSystemPromptTemplate;

    @Bean("helpDeskChatClient")
    public ChatClient helpDeskChatClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory, TimeTools timeTools) {
        Advisor simpleLoggerAdvisor = new SimpleLoggerAdvisor();
        Advisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return ChatClient
                .builder(openAiChatModel)
                .defaultSystem(helpDeskSystemPromptTemplate)
                .defaultTools(timeTools)
                .defaultAdvisors(List.of(simpleLoggerAdvisor, messageChatMemoryAdvisor))
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
