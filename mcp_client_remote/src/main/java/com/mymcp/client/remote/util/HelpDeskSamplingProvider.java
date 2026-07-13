package com.mymcp.client.remote.util;


//import com.openai.models.conversations.Message;

import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mcp.annotation.McpSampling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HelpDeskSamplingProvider {

    private final ChatModel chatModel;

    public HelpDeskSamplingProvider(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @McpSampling(clients = "remote")
    public McpSchema.CreateMessageResult handleSamplingRequest(McpSchema.CreateMessageRequest request) {
        log.info("Recieved MCP Sampling request from server for system prompt: {}", request.systemPrompt());

        List<Message> messages = new ArrayList<>();
        if (request.systemPrompt() != null && !request.systemPrompt().isBlank()) {
            messages.add(new SystemMessage(request.systemPrompt()));
        }

        String userPrompt = request
                .messages()
                .stream()
                .filter(message -> message.content() instanceof McpSchema.TextContent && message.role().name().equalsIgnoreCase(McpSchema.Role.USER.name()))
                .map(messageContent -> ((McpSchema.TextContent) messageContent.content()).text())
                .collect(Collectors.joining("/n"));

        messages.add(new UserMessage(userPrompt));

        //Call the LLM model directly via ChatModel to avoid re-triggering MCP tools.
        ChatResponse chatResponse = chatModel.call(new Prompt(messages));
        if (chatResponse == null) {
            throw new IllegalArgumentException("LLM returned no result for the MCP sampling request");
        }
        String model = chatResponse.getMetadata().getModel();
        String generateText = chatResponse.getResult().getOutput().getText();
        log.info("Generated MCP Sampling response from server for model: {}", model);
        log.info("Generated MCP Sampling response: {}", generateText);

        return McpSchema.CreateMessageResult
                .builder(McpSchema.Role.ASSISTANT, generateText, model)
                .build();
    }

}
