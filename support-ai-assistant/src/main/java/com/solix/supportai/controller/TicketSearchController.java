package com.solix.supportai.controller;

import com.solix.supportai.dto.TicketSearchRequest;
import com.solix.supportai.model.SupportTicket;
import com.solix.supportai.service.TicketSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketSearchController {

    private final TicketSearchService ticketSearchService;

    public TicketSearchController(TicketSearchService ticketSearchService) {
        this.ticketSearchService = ticketSearchService;
    }

    @PostMapping("/similar")
    public List<SupportTicket> searchSimilarTickets(
            @RequestBody TicketSearchRequest request) {

        return ticketSearchService.findSimilarTickets(request.getTicketId());
    }
}