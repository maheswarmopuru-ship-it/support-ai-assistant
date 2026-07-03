package com.solix.supportai.service;

import com.solix.supportai.model.SupportTicket;
import com.solix.supportai.repository.TicketKnowledgeRepository;
import org.springframework.stereotype.Service;
import com.solix.supportai.dto.SimilarTicketResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketSearchServiceImpl implements TicketSearchService {

    private final TicketKnowledgeRepository repository;

    public TicketSearchServiceImpl(TicketKnowledgeRepository repository) {
        this.repository = repository;
    }

    @Override
    public SimilarTicketResponse findSimilarTickets(String ticketId) {

        SupportTicket currentTicket = repository.findByTicketId(ticketId);

        if (currentTicket == null) {
            throw new RuntimeException("Ticket not found : " + ticketId);
        }

        List<SupportTicket> similarTickets = repository.findAll()
                .stream()
                .filter(ticket -> !ticket.getTicketId().equals(ticketId))
                .filter(ticket -> ticket.getSubject() != null)
                .sorted((t1, t2) -> {

                    int score1 = calculateScore(currentTicket.getSubject(), t1.getSubject());
                    int score2 = calculateScore(currentTicket.getSubject(), t2.getSubject());

                    return Integer.compare(score2, score1);

                })
                .filter(ticket ->
                        calculateScore(currentTicket.getSubject(),
                                ticket.getSubject()) >= 2)
                .limit(5)
                .toList();

        SimilarTicketResponse response = new SimilarTicketResponse();

        response.setCurrentTicket(currentTicket);
        response.setSimilarTickets(similarTickets);
        response.setTotalMatches(similarTickets.size());
        response.setSearchType("KEYWORD");

        return response;
    }

    // ✅ This method is OUTSIDE findSimilarTickets()
    private int calculateScore(String currentSubject,
                               String candidateSubject) {

        String[] keywords = currentSubject.toLowerCase().split("\\s+");

        candidateSubject = candidateSubject.toLowerCase();

        int score = 0;

        for (String keyword : keywords) {

            if (keyword.length() < 4)
                continue;

            if (candidateSubject.contains(keyword))
                score++;
        }

        return score;
    }
}