package com.solix.supportai.search;

import com.solix.supportai.dto.SimilarTicketResponse;
import com.solix.supportai.service.TicketSearchService;
import org.springframework.stereotype.Service;

@Service
public class SemanticSearchServiceImpl
        implements SemanticSearchService {

    private final TicketSearchService ticketSearchService;

    public SemanticSearchServiceImpl(
            TicketSearchService ticketSearchService) {

        this.ticketSearchService = ticketSearchService;

    }

    @Override
    public SimilarTicketResponse searchSimilarTickets(
            String ticketId) {

        return ticketSearchService.findSimilarTickets(ticketId);

    }

}