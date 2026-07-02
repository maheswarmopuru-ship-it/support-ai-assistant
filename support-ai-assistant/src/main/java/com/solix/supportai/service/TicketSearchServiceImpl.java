package com.solix.supportai.service;

import com.solix.supportai.model.SupportTicket;
import com.solix.supportai.repository.TicketKnowledgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketSearchServiceImpl implements TicketSearchService {

    private final TicketKnowledgeRepository repository;

    public TicketSearchServiceImpl(TicketKnowledgeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SupportTicket> searchTickets(String subject,
                                             String description) {

        return repository.getAllTickets();

    }
}
