package com.solix.supportai.controller;

import com.solix.supportai.dto.SimilarTicketResponse;
import com.solix.supportai.dto.TicketSearchRequest;
import com.solix.supportai.service.TicketSearchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketSearchController {

    private final TicketSearchService ticketSearchService;

    public TicketSearchController(TicketSearchService ticketSearchService) {
        this.ticketSearchService = ticketSearchService;
    }

    @PostMapping("/similar")
    public SimilarTicketResponse searchSimilarTickets(
            @RequestBody TicketSearchRequest request) {

        return ticketSearchService.findSimilarTickets(request.getTicketId());
    }
}