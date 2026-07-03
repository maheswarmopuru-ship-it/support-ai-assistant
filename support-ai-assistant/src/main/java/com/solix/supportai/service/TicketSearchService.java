package com.solix.supportai.service;

import com.solix.supportai.dto.SimilarTicketResponse;

public interface TicketSearchService {

    SimilarTicketResponse findSimilarTickets(String ticketId);

}