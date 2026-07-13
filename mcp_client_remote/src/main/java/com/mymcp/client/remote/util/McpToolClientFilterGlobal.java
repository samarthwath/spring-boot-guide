package com.mymcp.client.remote.util;

import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.McpConnectionInfo;
import org.springframework.ai.mcp.McpToolFilter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class McpToolClientFilterGlobal implements McpToolFilter {
    @Override
    public boolean test(McpConnectionInfo mcpConnectionInfo, McpSchema.Tool tool) {
        String serverName = mcpConnectionInfo
                .initializeResult()
                .serverInfo()
                .name();
        String toolName = tool.name();
        log.info("Evaluating tool {} from mcp server: {}", toolName, serverName);

        if (serverName.toLowerCase().contains("github")) {
            log.info("Tool {} rejected because it belongs to blocked mcp server {}", toolName, serverName);
            return false;
        }
        log.info("Tool {} approved from mcp server {}", toolName, serverName);
        return true;
    }
}
