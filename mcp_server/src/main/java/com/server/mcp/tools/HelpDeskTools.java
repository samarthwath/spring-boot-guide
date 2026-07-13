package com.server.mcp.tools;

import com.server.mcp.entity.HelpDeskTicket;
import com.server.mcp.model.TicketRequest;
import com.server.mcp.service.HelpDeskTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class HelpDeskTools {
    private HelpDeskTicketService helpDeskTicketService;

    public HelpDeskTools(HelpDeskTicketService helpDeskTicketService) {
        this.helpDeskTicketService = helpDeskTicketService;
    }

    @McpTool(name = "createHelpDeskTicket", description = "Create support ticket")
    public String createHelpDeskTicket(@McpToolParam(required = true, description = "Details to create a support ticket") TicketRequest ticketRequest) {
        String username=ticketRequest.username();
        log.info("Creating help desk ticket by considering ticket request details for username : {}, {}", ticketRequest, username);
        HelpDeskTicket savedHelpDeskTicket = helpDeskTicketService.save(ticketRequest);
        log.info("Created help desk ticket for username : {}, {}", username, savedHelpDeskTicket);
        return "Ticket create for user: " + username + " with ticket id: " + savedHelpDeskTicket.getId();
    }

    @McpTool(name = "getTicketByUsername", description = "Fetch the status of open tickets by there username")
    public List<HelpDeskTicket> getTicketByUsername(String username) {
        List<HelpDeskTicket> ticketsByUsername = helpDeskTicketService.getTicketsByUsername(username);
        log.info("Found {} help desk tickets by username : {}", ticketsByUsername.size(), username);
        return ticketsByUsername;
    }

}
