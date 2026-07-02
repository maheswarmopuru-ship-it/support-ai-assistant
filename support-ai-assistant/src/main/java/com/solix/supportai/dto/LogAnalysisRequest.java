package com.solix.supportai.dto;

public class LogAnalysisRequest {

    private String log;

    public LogAnalysisRequest() {
    }

    public LogAnalysisRequest(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}