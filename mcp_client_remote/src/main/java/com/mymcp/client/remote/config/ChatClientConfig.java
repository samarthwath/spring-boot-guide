package com.mymcp.client.remote.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean("mcpChatClient")
    public ChatClient chatClient(OpenAiChatModel openAiChatModel, ToolCallbackProvider toolCallbackProvider) {
        return
                ChatClient
                        .builder(openAiChatModel)
                        .defaultTools(toolCallbackProvider)
                        .defaultAdvisors(List.of(new SimpleLoggerAdvisor()))
                        .build();
    }

    @Bean("mcpChatClientPerRequestToolFilter")
    public ChatClient mcpChatClientPerRequestToolFilter(OpenAiChatModel openAiChatModel) {
        return
                ChatClient
                        .builder(openAiChatModel)
                        .defaultAdvisors(List.of(new SimpleLoggerAdvisor()))
                        .build();
    }
}
