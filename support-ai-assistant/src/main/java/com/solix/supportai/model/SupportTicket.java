package com.solix.supportai.model;

public class SupportTicket {

    private Long ticketId;

    private String company;

    private String productService;

    private String subject;

    private String description;

    private String supportComments;

    private String priority;

    private String status;

    private String severity;

    public SupportTicket() {
    }

    public SupportTicket(Long ticketId,
                         String company,
                         String productService,
                         String subject,
                         String description,
                         String supportComments,
                         String priority,
                         String status,
                         String severity) {

        this.ticketId = ticketId;
        this.company = company;
        this.productService = productService;
        this.subject = subject;
        this.description = description;
        this.supportComments = supportComments;
        this.priority = priority;
        this.status = status;
        this.severity = severity;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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