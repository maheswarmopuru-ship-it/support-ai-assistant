package com.solix.supportai.dto;

import com.solix.supportai.model.SupportTicket;
import com.solix.supportai.dto.SimilarTicketResponse;

import java.util.List;

public class SimilarTicketResponse {

    private SupportTicket currentTicket;

    private List<SupportTicket> similarTickets;

    private int totalMatches;

    private String searchType;

    public SimilarTicketResponse() {
    }

    public SupportTicket getCurrentTicket() {
        return currentTicket;
    }

    public void setCurrentTicket(SupportTicket currentTicket) {
        this.currentTicket = currentTicket;
    }

    public List<SupportTicket> getSimilarTickets() {
        return similarTickets;
    }

    public void setSimilarTickets(List<SupportTicket> similarTickets) {
        this.similarTickets = similarTickets;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}