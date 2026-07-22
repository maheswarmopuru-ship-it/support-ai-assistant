package com.solix.supportai.service;

import com.solix.supportai.dto.SimilarTicketResponse;
import com.solix.supportai.embedding.EmbeddingService;
import com.solix.supportai.knowledge.KnowledgeExtractionService;
import com.solix.supportai.model.SupportTicket;
import com.solix.supportai.qdrant.QdrantService;
import com.solix.supportai.repository.TicketKnowledgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TicketSearchServiceImpl implements TicketSearchService {

    private final TicketKnowledgeRepository repository;
    private final EmbeddingService embeddingService;
    private final KnowledgeExtractionService knowledgeExtractionService;
    private final QdrantService qdrantService;

    public TicketSearchServiceImpl(
            TicketKnowledgeRepository repository,
            EmbeddingService embeddingService,
            KnowledgeExtractionService knowledgeExtractionService,
            QdrantService qdrantService) {

        this.repository = repository;
        this.embeddingService = embeddingService;
        this.knowledgeExtractionService = knowledgeExtractionService;
        this.qdrantService = qdrantService;
    }

    @Override
    public SimilarTicketResponse findSimilarTickets(String ticketId) {

        SupportTicket currentTicket = repository.findByTicketId(ticketId);

        if (currentTicket == null) {
            throw new RuntimeException("Ticket not found : " + ticketId);
        }

        String technicalKnowledge =
                knowledgeExtractionService.extractTechnicalKnowledge(
                        currentTicket.getSupportComments());

        String searchableText = """
        Subject:
        %s

        Technical Knowledge:
        %s

        Product:
        %s
        """.formatted(
                currentTicket.getSubject() == null ? "" : currentTicket.getSubject(),
                technicalKnowledge == null ? "" : technicalKnowledge,
                currentTicket.getProductService() == null ? "" : currentTicket.getProductService()
        );
        // Generate embedding
        List<Double> embedding =
                embeddingService.generateEmbedding(searchableText);

        // Search similar tickets in Qdrant
        List<String> similarTicketIds =
                qdrantService.searchSimilarTickets(
                        "support_tickets",
                        embedding,
                        6);

        // Load ticket objects
        List<SupportTicket> similarTickets = similarTicketIds.stream()
                .filter(id -> !id.equalsIgnoreCase(ticketId))
                .map(repository::findByTicketId)
                .filter(Objects::nonNull)
                .limit(5)
                .toList();

        SimilarTicketResponse response = new SimilarTicketResponse();
        response.setCurrentTicket(currentTicket);
        response.setSimilarTickets(similarTickets);
        response.setTotalMatches(similarTickets.size());
        response.setSearchType("SEMANTIC");

        return response;
    }
}