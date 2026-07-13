package com.mymcp.client.remote.controller;

import com.mymcp.client.remote.util.ToolUtil;
import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/mcp")
public class MCPChatClientController {

    private final List<McpSyncClient> mcpSyncClients;

    private final ChatClient chatClient;

    private final ChatClient mcpChatClientPerRequestToolFilter;

    public MCPChatClientController(@Qualifier("mcpChatClient") ChatClient chatClient, @Qualifier("mcpChatClientPerRequestToolFilter") ChatClient mcpChatClientPerRequestToolFilter, List<McpSyncClient> mcpSyncClients) {
        this.chatClient = chatClient;
        this.mcpChatClientPerRequestToolFilter = mcpChatClientPerRequestToolFilter;
        this.mcpSyncClients = mcpSyncClients;
    }

    @GetMapping("chat")
    public ResponseEntity<String> mcpChatClient(@RequestParam String message, @RequestHeader(value = "username", required = false) String username) {
        String content = chatClient
                .prompt()
                .user(message + " My username is: " + username)
                .call()
                .content();
        return ResponseEntity.ok(content);
    }

    @GetMapping("/help-desk/chat")
    public ResponseEntity<String> mcpHelpDeskChatClient(@RequestParam String message, @RequestHeader(value = "username", required = false) String username) {
        ToolCallback[] toolCallbacks = ToolUtil.selectToolsFor(mcpSyncClients, "helpdesk-mcp-server", null);
        String content = mcpChatClientPerRequestToolFilter
                .prompt()
                .tools(toolCallbacks)
                .toolContext(Map.of("progressToken", UUID.randomUUID().toString()))
                .user(message + " My username is: " + username)
                .call()
                .content();
        return ResponseEntity.ok(content);
    }

    @GetMapping("/help-desk/summarize-tickets")
    public ResponseEntity<String> mcpHelpDeskTicketSummarizer(@RequestHeader(value = "username", required = false) String username) {
        ToolCallback[] toolCallbacks = ToolUtil.selectToolsFor(mcpSyncClients, "helpdesk-mcp-server", null);
        String content = mcpChatClientPerRequestToolFilter
                .prompt()
                .system("""
                        You orchestrate the 'summarizeTickets' tool. The tool already returns a complete,
                        customer-ready summary that was generated for this exact request. Return that tool
                        output to the user EXACTLY as-is: do not rewrite, reformat, shorten, expand,
                        rephrase, or add any commentary of your own. Your reply must be the verbatim tool
                        response and nothing else.
                        """)
                .tools(toolCallbacks)
                .toolContext(Map.of("progressToken", UUID.randomUUID().toString()))
                .user("Summarize All support tickets: " + " My username is: " + username)
                .call()
                .content();
        return ResponseEntity.ok(content);
    }


}
