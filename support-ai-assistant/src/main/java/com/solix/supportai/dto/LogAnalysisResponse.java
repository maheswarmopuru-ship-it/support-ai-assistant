package com.solix.supportai.dto;

public class LogAnalysisResponse {

    private String analysis;

    public LogAnalysisResponse() {
    }

    public LogAnalysisResponse(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}