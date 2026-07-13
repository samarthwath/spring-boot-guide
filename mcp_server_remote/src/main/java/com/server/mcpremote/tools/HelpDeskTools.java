package com.server.mcpremote.tools;

import com.server.mcpremote.entity.HelpDeskTicket;
import com.server.mcpremote.model.TicketContactInfo;
import com.server.mcpremote.model.TicketRequest;
import com.server.mcpremote.service.HelpDeskTicketService;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.annotation.McpElicitation;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.ai.mcp.annotation.context.McpSyncRequestContext;
import org.springframework.ai.mcp.annotation.context.StructuredElicitResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HelpDeskTools {
    private HelpDeskTicketService helpDeskTicketService;
    private static final String DEFAULT_CONTACT_PHONE = "N/A";
    private static final String DEFAULT_PRIORITY = "MEDIUM";

    public HelpDeskTools(HelpDeskTicketService helpDeskTicketService) {
        this.helpDeskTicketService = helpDeskTicketService;
    }

    @McpTool(name = "createHelpDeskTicket", description = "Create support ticket")
    public String createHelpDeskTicket(@McpToolParam(required = true, description = "Details to create a support ticket") TicketRequest ticketRequest, McpSyncRequestContext ctx) {
        String username = ticketRequest.username();
        ctx.info("Creating a support ticket with username:" + username);
        ctx.info("Creating a support ticket with request:" + ticketRequest);
        log.info("Creating help desk ticket by considering ticket request details for username : {}, {}", ticketRequest, username);
        String contactPhone = DEFAULT_CONTACT_PHONE;
        String priority = DEFAULT_PRIORITY;
        if (ctx.elicitEnabled()) {
            StructuredElicitResult<TicketContactInfo> elicitResult = ctx.elicit(elicitationSpec -> elicitationSpec.message("Before we open our support ticket please choose a priority " + " LOW, MEDIUM, HIGH " + "and share a contact phone number so our support team can reach you "), TicketContactInfo.class);
            log.info("Elicit finished with action: {}", elicitResult.action().toString());
            ctx.info("Elicit finished with action: " + elicitResult.action().toString());
            if (elicitResult.action() == McpSchema.ElicitResult.Action.ACCEPT && elicitResult.structuredContent() != null) {
                TicketContactInfo ticketContactInfo = elicitResult.structuredContent();
                if (ticketContactInfo.contactPhone() != null && !ticketContactInfo.contactPhone().isBlank()) {
                    contactPhone = ticketContactInfo.contactPhone();
                }
                if (ticketContactInfo.priority() != null && !ticketContactInfo.priority().isBlank()) {
                    priority = ticketContactInfo.priority();
                }
            } else if (elicitResult.action() == McpSchema.ElicitResult.Action.DECLINE) {
                log.info("Elicit action is decline");
                ctx.info("Elicit action is decline");
            } else if (elicitResult.action() == McpSchema.ElicitResult.Action.CANCEL) {
                log.info("Elicit action is cancel");
                ctx.info("Elicit action is cancel");
            }
        } else {
            log.info("MCP Client does not support elicitation. Using default settings");
            ctx.info("MCP Client does not support elicitation. Using default settings");
        }
        HelpDeskTicket savedHelpDeskTicket = helpDeskTicketService.save(ticketRequest, priority, contactPhone);
        ctx.info("Created help desk ticket: " + savedHelpDeskTicket);
        log.info("Created help desk ticket for username : {}, {}", username, savedHelpDeskTicket);
        return "Ticket created for user: " + username + " with ticket id: " + savedHelpDeskTicket.getId();
    }

    @McpTool(name = "getTicketByUsername", description = "Fetch the status of open tickets by there username")
    public List<HelpDeskTicket> getTicketByUsername(String username, McpSyncRequestContext ctx) throws InterruptedException {
        ctx.info("Fetching tickets by username: " + username);
        List<HelpDeskTicket> ticketsByUsername = helpDeskTicketService.getTicketsByUsername(username);
        ctx.info("Found tickets by username: " + ticketsByUsername);
        log.info("Found {} help desk tickets by username : {}", ticketsByUsername.size(), username);
        for (int i = 0; i < 10; i++) {
            int percentage = i * 100 / 10;
            Thread.sleep(1000);
            ctx.progress(progressSpec -> progressSpec
                    .percentage(percentage)
                    .message("Fetching tickets for username: " + username + " - " + percentage + " % complete")
            );
        }
        return ticketsByUsername;
    }

    @McpTool(name = "summarizeTickets", description = "Summarize ticket based on the username")
    public String summarizeTickets(@McpToolParam String username, McpSyncRequestContext ctx) {
        List<HelpDeskTicket> ticketsByUsername = helpDeskTicketService
                .getTicketsByUsername(username);
        ctx.info("Found tickets by username: " + ticketsByUsername);
        log.info("Found tickets by username: " + ticketsByUsername.size());
        if (ticketsByUsername.isEmpty()) {
            ctx.info("No tickets found for username: " + username);
            return "No tickets found for username: " + username;
        }
        if (!ctx.sampleEnabled()) {
            ctx.info("Sampling is disabled");
            return ticketsByUsername.toString();
        }

        String ticketSummaryDetails = ticketsByUsername
                .stream()
                .map(ticketInfo -> "Ticket id: " + ticketInfo.getId() +
                        "Issue: " + ticketInfo.getIssue() +
                        "Username: " + ticketInfo.getUsername() +
                        "ETA: " + ticketInfo.getEta() +
                        "Status: " + ticketInfo.getStatus() +
                        "Created at: " + ticketInfo.getCreatedAt()
                )
                .collect(Collectors.joining("\n"));
        String systemPrompt = """
                You are a friendly help desk assistant. Using ONLY the ticket data provided by the user,
                write a short, warm summary for the customer about the status of their support tickets.
                Mention how many tickets they have in total, group them by status (OPEN, IN_PROGRESS, CLOSED),
                and reassure them about the ones that are still being worked on. Keep it under 120 words and
                do not invent any information that is not present in the ticket data.
                """;

        McpSchema.CreateMessageResult createMessageResult = ctx
                .sample(samplingSpec -> samplingSpec
                        .systemPrompt(systemPrompt)
                        .message("Here are the support ticket for username: " + ":\n" + ticketSummaryDetails)
                );

        String summarizedContent = ((McpSchema.TextContent) createMessageResult.content()).text();
        log.info("Summarized content: " + summarizedContent);
        ctx.info("Summarized content: " + summarizedContent);
        return summarizedContent;
    }

}
