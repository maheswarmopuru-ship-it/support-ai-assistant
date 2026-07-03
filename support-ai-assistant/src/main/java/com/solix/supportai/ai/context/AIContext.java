package com.solix.supportai.ai.context;

import java.util.List;

public class AIContext {

    private String currentTicketId;

    private String currentSubject;

    private List<String> historicalIncidents;

    public String getCurrentTicketId() {
        return currentTicketId;
    }

    public void setCurrentTicketId(String currentTicketId) {
        this.currentTicketId = currentTicketId;
    }

    public String getCurrentSubject() {
        return currentSubject;
    }

    public void setCurrentSubject(String currentSubject) {
        this.currentSubject = currentSubject;
    }

    public List<String> getHistoricalIncidents() {
        return historicalIncidents;
    }

    public void setHistoricalIncidents(List<String> historicalIncidents) {
        this.historicalIncidents = historicalIncidents;
    }
}