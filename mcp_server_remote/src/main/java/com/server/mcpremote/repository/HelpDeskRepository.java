package com.server.mcpremote.repository;

import com.server.mcpremote.entity.HelpDeskTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpDeskRepository extends JpaRepository<HelpDeskTicket, Long> {

    List<HelpDeskTicket>  findByUsername(String username);
}
