package com.solix.supportai.repository;

import com.solix.supportai.loader.ExcelTicketLoader;
import com.solix.supportai.model.SupportTicket;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TicketKnowledgeRepository {

    private final List<SupportTicket> tickets = new ArrayList<>();

    private final Map<String, SupportTicket> ticketIndex = new HashMap<>();

    public TicketKnowledgeRepository(ExcelTicketLoader loader) {

        tickets.addAll(loader.loadTickets());

        for (SupportTicket ticket : tickets) {

            ticketIndex.put(ticket.getTicketId(), ticket);

        }

        System.out.println("Tickets Loaded : " + tickets.size());

    }

    public List<SupportTicket> findAll() {

        return tickets;

    }

    public SupportTicket findByTicketId(String ticketId) {

        return ticketIndex.get(ticketId);

    }

}