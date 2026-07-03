package com.solix.supportai.dto;

public class AIRecommendationRequest {

    private String ticketId;

    public AIRecommendationRequest() {
    }

    public AIRecommendationRequest(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}