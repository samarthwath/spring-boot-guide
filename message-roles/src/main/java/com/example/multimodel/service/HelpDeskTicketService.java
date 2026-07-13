package com.example.multimodel.service;

import com.example.multimodel.entity.HelpDeskTicket;
import com.example.multimodel.model.TicketRequest;
import com.example.multimodel.repositories.HelpDeskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketService {

    private final HelpDeskRepository helpDeskRepository;

    public HelpDeskTicket save(TicketRequest ticketRequest, String username) {
        HelpDeskTicket helpDeskTicket = HelpDeskTicket
                .builder()
                .issue(ticketRequest.issue())
                .username(username)
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
