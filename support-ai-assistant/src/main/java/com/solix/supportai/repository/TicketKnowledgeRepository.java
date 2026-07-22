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

            System.out.println("Loaded Ticket: [" + ticket.getTicketId() + "]");

            if (ticket.getTicketId() != null &&
                    !ticket.getTicketId().trim().isEmpty()) {

                String normalizedId = ticket.getTicketId()
                        .trim()
                        .replace("\\", "/")
                        .toUpperCase();

                ticketIndex.put(normalizedId, ticket);
            }
        }
        System.out.println("Total Tickets Loaded : " + tickets.size());
        System.out.println("Total Indexed Tickets : " + ticketIndex.size());
    }

    public List<SupportTicket> findAll() {

        return tickets;

    }
    public SupportTicket findByTicketId(String ticketId) {

        if (ticketId == null) {
            return null;
        }

        String normalizedId = ticketId
                .trim()
                .replace("\\", "/")
                .toUpperCase();

        return ticketIndex.get(normalizedId);
    }

}