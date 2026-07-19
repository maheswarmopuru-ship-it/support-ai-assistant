package com.solix.supportai.ai.builder;

import com.solix.supportai.ai.context.AIContext;
import com.solix.supportai.knowledge.KnowledgeExtractionService;
import com.solix.supportai.model.SupportTicket;
import com.solix.supportai.repository.TicketKnowledgeRepository;
import com.solix.supportai.search.SemanticSearchService;
import org.springframework.stereotype.Component;
import com.solix.supportai.dto.SimilarTicketResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AIContextBuilderImpl implements AIContextBuilder {

    private final TicketKnowledgeRepository repository;
    private final SemanticSearchService semanticSearchService;
    private final KnowledgeExtractionService knowledgeExtractionService;

    public AIContextBuilderImpl(TicketKnowledgeRepository repository,
                                SemanticSearchService semanticSearchService,
                                KnowledgeExtractionService knowledgeExtractionService) {
        this.repository = repository;
        this.semanticSearchService = semanticSearchService;
        this.knowledgeExtractionService = knowledgeExtractionService;
    }

    @Override
    public AIContext build(String ticketId) {

        SupportTicket currentTicket = repository.findByTicketId(ticketId);

        if (currentTicket == null) {
            throw new RuntimeException("Ticket not found : " + ticketId);
        }

        SimilarTicketResponse response =
                semanticSearchService.searchSimilarTickets(ticketId);

        List<SupportTicket> similarTickets =
                response.getSimilarTickets();

        AIContext context = new AIContext();

        context.setCurrentTicketId(currentTicket.getTicketId());
        context.setCurrentSubject(currentTicket.getSubject());

        context.setHistoricalIncidents(

                similarTickets.stream()

                        .map(ticket ->

                                "Subject : " + ticket.getSubject()
                                        + "\nTechnical Notes : "
                                        + knowledgeExtractionService.extractTechnicalKnowledge(
                                        ticket.getSupportComments()))

                        .collect(Collectors.toList())

        );

        return context;
    }
}