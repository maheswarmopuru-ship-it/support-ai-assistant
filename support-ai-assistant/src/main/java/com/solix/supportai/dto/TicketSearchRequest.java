package com.solix.supportai.dto;

public class TicketSearchRequest {

    private String ticketId;

    public TicketSearchRequest() {
    }

    public TicketSearchRequest(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}