package com.solix.supportai.model;

public class SupportTicket {

    private String ticketId;
    private String assignedTo;
    private String reportedBy;
    private String company;
    private String reportedDate;
    private String ageInDays;
    private String modifiedDate;
    private String productService;
    private String subject;
    private String supportComments;
    private String priority;
    private String subStatus;
    private String status;
    private String severity;

    public SupportTicket() {
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getAgeInDays() {
        return ageInDays;
    }

    public void setAgeInDays(String ageInDays) {
        this.ageInDays = ageInDays;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getProductService() {
        return productService;
    }

    public void setProductService(String productService) {
        this.productService = productService;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSupportComments() {
        return supportComments;
    }

    public void setSupportComments(String supportComments) {
        this.supportComments = supportComments;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}