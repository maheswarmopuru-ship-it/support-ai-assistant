package com.solix.supportai.search;

import com.solix.supportai.dto.SimilarTicketResponse;

public interface SemanticSearchService {

    SimilarTicketResponse searchSimilarTickets(String ticketId);

}