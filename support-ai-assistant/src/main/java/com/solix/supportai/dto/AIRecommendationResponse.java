package com.solix.supportai.dto;

public class AIRecommendationResponse {

    private String ticketId;

    private String recommendation;

    public AIRecommendationResponse() {
    }

    public AIRecommendationResponse(String ticketId, String recommendation) {
        this.ticketId = ticketId;
        this.recommendation = recommendation;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}