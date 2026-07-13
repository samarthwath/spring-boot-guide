package com.server.mcp.service;

import com.server.mcp.entity.HelpDeskTicket;
import com.server.mcp.model.TicketRequest;
import com.server.mcp.repository.HelpDeskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HelpDeskTicketService {
    private final HelpDeskRepository helpDeskRepository;

    public HelpDeskTicketService(HelpDeskRepository helpDeskRepository) {
        this.helpDeskRepository = helpDeskRepository;
    }


    public HelpDeskTicket save(TicketRequest ticketRequest) {
        HelpDeskTicket helpDeskTicket = HelpDeskTicket
                .builder()
                .issue(ticketRequest.issue())
                .username(ticketRequest.username())
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(7))
                .build();
        return helpDeskRepository.save(helpDeskTicket);
    }

    public HelpDeskTicket findById(Long id) {
        return helpDeskRepository.findById(id).orElse(null);
    }

    public List<HelpDeskTicket> getTicketsByUsername(String username) {
        return helpDeskRepository.findByUsername(username);
    }

}
