package com.solix.supportai.qdrant.indexer;

import com.solix.supportai.embedding.EmbeddingService;
import com.solix.supportai.knowledge.KnowledgeExtractionService;
import com.solix.supportai.model.SupportTicket;
import com.solix.supportai.qdrant.QdrantService;
import com.solix.supportai.repository.TicketKnowledgeRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TicketEmbeddingIndexer {
    private static final Logger logger =
            LoggerFactory.getLogger(TicketEmbeddingIndexer.class);

    private final TicketKnowledgeRepository repository;
    private final EmbeddingService embeddingService;
    private final KnowledgeExtractionService knowledgeExtractionService;
    private final QdrantService qdrantService;

    public TicketEmbeddingIndexer(
            TicketKnowledgeRepository repository,
            EmbeddingService embeddingService,
            KnowledgeExtractionService knowledgeExtractionService,
            QdrantService qdrantService) {

        this.repository = repository;
        this.embeddingService = embeddingService;
        this.knowledgeExtractionService = knowledgeExtractionService;
        this.qdrantService = qdrantService;
    }

    @PostConstruct
    public void indexFirstTicket() {

        List<SupportTicket> tickets = repository.findAll();

        SupportTicket ticket = tickets.get(0);

        logger.info("Indexing ticket : {}", ticket.getTicketId());

        String technicalKnowledge =
                knowledgeExtractionService.extractTechnicalKnowledge(
                        ticket.getSupportComments());

        String embeddingText = """
            Subject:
            %s

            Technical Knowledge:
            %s

            Product:
            %s
            """.formatted(
                ticket.getSubject(),
                technicalKnowledge,
                ticket.getProductService());

        List<Double> vector =
                embeddingService.generateEmbedding(embeddingText);

        logger.info("Embedding dimension : {}", vector.size());

        Map<String, Object> payload = Map.of(
                "ticketId", ticket.getTicketId(),
                "subject", ticket.getSubject(),
                "company", ticket.getCompany(),
                "product", ticket.getProductService(),
                "priority", ticket.getPriority(),
                "status", ticket.getStatus()
        );

        qdrantService.upsertPoint(
                "support_tickets",
                1L,
                vector,
                payload);

        logger.info("Ticket uploaded to Qdrant.");

    }
}