package com.solix.supportai.dto;

import com.solix.supportai.model.SupportTicket;

import java.util.List;

public class SimilarTicketResponse {

    private List<SupportTicket> tickets;

    public SimilarTicketResponse() {
    }

    public SimilarTicketResponse(List<SupportTicket> tickets) {
        this.tickets = tickets;
    }

    public List<SupportTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<SupportTicket> tickets) {
        this.tickets = tickets;
    }

}