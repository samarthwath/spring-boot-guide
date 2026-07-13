package com.mymcp.client.remote.util;

import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.annotation.McpLogging;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelpDeskLogBridge {

    @McpLogging(clients = "remote")
    public void onServerLog(McpSchema.LoggingLevel loggingLevel, String source, String message) {
        log.info("Received log from server - Level: {}, Source: {}, Message {}", loggingLevel, source, message);
    }
}
