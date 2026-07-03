package com.solix.supportai.controller;

import com.solix.supportai.dto.AIRecommendationRequest;
import com.solix.supportai.dto.AIRecommendationResponse;
import com.solix.supportai.dto.LogAnalysisRequest;
import com.solix.supportai.dto.LogAnalysisResponse;
import com.solix.supportai.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String question) {

        return aiService.askAI(question);

    }
    @PostMapping("/analyze-log")
    public LogAnalysisResponse analyzeLog(@RequestBody LogAnalysisRequest request) {

        String response = aiService.analyzeLog(request.getLog());

        return new LogAnalysisResponse(response);
    }
    @PostMapping("/recommendation")
    public AIRecommendationResponse recommendation(
            @RequestBody AIRecommendationRequest request) {

        return aiService.generateRecommendation(request.getTicketId());
    }

}
