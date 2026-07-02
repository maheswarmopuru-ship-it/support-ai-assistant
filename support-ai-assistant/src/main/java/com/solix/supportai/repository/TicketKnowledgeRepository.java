package com.solix.supportai.repository;

import com.solix.supportai.model.SupportTicket;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketKnowledgeRepository {

    private final List<SupportTicket> tickets = new ArrayList<>();

    public List<SupportTicket> getAllTickets() {

        return tickets;

    }
    public void addTickets(List<SupportTicket> newTickets) {
        tickets.addAll(newTickets);
    }

}