package com.example.multimodel.tools;


import com.example.multimodel.entity.HelpDeskTicket;
import com.example.multimodel.model.TicketRequest;
import com.example.multimodel.service.HelpDeskTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class HelpDeskTools {
    private HelpDeskTicketService helpDeskTicketService;

    public HelpDeskTools(HelpDeskTicketService helpDeskTicketService) {
        this.helpDeskTicketService = helpDeskTicketService;
    }

    @Tool(name = "createHelpDeskTicket", description = "Create support ticket", returnDirect = true)
    public String createHelpDeskTicket(@ToolParam(required = true, description = "Details to create a support ticket") TicketRequest ticketRequest, ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");
        log.info("Creating help desk ticket by considering ticket request details for username : {}, {}", ticketRequest, username);
        HelpDeskTicket savedHelpDeskTicket = helpDeskTicketService.save(ticketRequest, username);
        log.info("Created help desk ticket for username : {}, {}", username, savedHelpDeskTicket);
        return "Ticket create for user: " + username + " with ticket id: " + savedHelpDeskTicket.getId();
    }

    @Tool(name = "getTicketByUsername", description = "Fetch the status of open tickets by there username")
    public List<HelpDeskTicket> getTicketByUsername(ToolContext toolContext) {
        log.info("Getting help desk ticket by username : {}", toolContext.getContext().get("username"));
        String username = (String) toolContext.getContext().get("username");
        List<HelpDeskTicket> ticketsByUsername = helpDeskTicketService.getTicketsByUsername(username);
        log.info("Found {} help desk tickets by username : {}", ticketsByUsername.size(), username);
        return ticketsByUsername;
    }

}
