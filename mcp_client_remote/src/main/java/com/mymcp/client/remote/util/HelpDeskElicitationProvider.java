package com.mymcp.client.remote.util;

import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.annotation.McpElicitation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class HelpDeskElicitationProvider {

    @McpElicitation(clients = "remote")
    public McpSchema.ElicitResult handleElicitRequest(McpSchema.ElicitRequest elicitRequest) {
        log.info("Received Elicitation Request from server: {}", elicitRequest);
        Map<String, Object> userResponse = Map.of("contactPhone", "8962881641", "priority", "MEDIUM");
        log.info("Elicitation response from server: {}", userResponse);
        return McpSchema
                .ElicitResult
                .builder(McpSchema.ElicitResult.Action.ACCEPT)
                .content(userResponse)
                .build();
    }
}
