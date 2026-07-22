package com.solix.supportai.controller;

import com.solix.supportai.dto.SimilarTicketResponse;
import com.solix.supportai.search.SemanticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SemanticSearchController {

    private final SemanticSearchService semanticSearchService;

    @GetMapping("/api/semantic-search")
    public SimilarTicketResponse search(
            @RequestParam String ticketId) {

        return semanticSearchService.searchSimilarTickets(ticketId);
    }
}