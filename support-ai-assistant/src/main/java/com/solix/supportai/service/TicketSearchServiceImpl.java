package com.solix.supportai.service;

import com.solix.supportai.model.SupportTicket;
import com.solix.supportai.repository.TicketKnowledgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketSearchServiceImpl implements TicketSearchService {

    private final TicketKnowledgeRepository repository;

    public TicketSearchServiceImpl(TicketKnowledgeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SupportTicket> findSimilarTickets(String ticketId) {

        SupportTicket currentTicket = repository.findByTicketId(ticketId);

        if (currentTicket == null) {
            throw new RuntimeException("Ticket not found : " + ticketId);
        }

        String[] keywords = currentTicket.getSubject()
                .toLowerCase()
                .split("\\s+");

        return repository.findAll()
                .stream()
                .filter(ticket -> !ticket.getTicketId().equals(ticketId))
                .filter(ticket -> {

                    if (ticket.getSubject() == null)
                        return false;

                    String subject = ticket.getSubject().toLowerCase();

                    int matches = 0;

                    for (String keyword : keywords) {

                        if (keyword.length() < 4)
                            continue;

                        if (subject.contains(keyword))
                            matches++;
                    }

                    return matches >= 2;

                })
                .limit(5)
                .toList();
    }
}