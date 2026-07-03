package com.solix.supportai.service;
import com.solix.supportai.dto.AIRecommendationResponse;

public interface AIService {
    String askAI(String prompt);
    String analyzeLog(String log);
    AIRecommendationResponse generateRecommendation(String ticketId);
}
