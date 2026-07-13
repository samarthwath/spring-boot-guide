package com.server.mcpremote.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "helpdesk_tickets")
public class HelpDeskTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String issue;
    private String status; //OPEN, IN_PROGRESS, CLOSED
    private LocalDateTime createdAt;
    private LocalDateTime eta;
    private String contactPhone;
    private String priority; //LOW, MEDIUM, HIGH


}

