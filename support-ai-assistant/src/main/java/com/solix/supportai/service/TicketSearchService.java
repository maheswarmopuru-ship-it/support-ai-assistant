package com.solix.supportai.service;

import com.solix.supportai.model.SupportTicket;

import java.util.List;

public interface TicketSearchService {

    List<SupportTicket> findSimilarTickets(String ticketId);

}