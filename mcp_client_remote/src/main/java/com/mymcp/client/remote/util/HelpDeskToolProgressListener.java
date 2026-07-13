package com.mymcp.client.remote.util;

import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.annotation.McpProgress;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelpDeskToolProgressListener {

    @McpProgress(clients = "remote")
    public void onProgress(McpSchema.ProgressNotification progressNotification) {
        log.info("Progress update: {}%, complete received request for Request ID id: {}, Message: {}",
                progressNotification.progress(),
                progressNotification.progressToken(),
                progressNotification.message()
        );
    }
}
