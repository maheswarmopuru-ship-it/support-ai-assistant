package com.solix.supportai.service;

import com.solix.supportai.ai.context.AIContext;
import com.solix.supportai.dto.AIRecommendationResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import com.solix.supportai.ai.builder.AIContextBuilder;
import com.solix.supportai.prompt.PromptTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AIServiceImpl implements AIService {
    private static final Logger logger =
            LoggerFactory.getLogger(AIServiceImpl.class);
    private final ChatClient chatClient;
    private final AIContextBuilder contextBuilder;
    private final PromptTemplateBuilder promptBuilder;

    public AIServiceImpl(ChatClient.Builder builder,AIContextBuilder contextBuilder,PromptTemplateBuilder promptBuilder) {
        this.chatClient = builder.build();
        this.contextBuilder = contextBuilder;
        this.promptBuilder = promptBuilder;
    }

    @Override
    public String askAI(String prompt) {

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();

    }
    @Override
    public String analyzeLog(String log) {

        String prompt = """
            You are an experienced enterprise support engineer.

            Analyze the following application log.

            Provide the response in the following format:

            Error Summary:
            Root Cause:
            Suggested Resolution:
            Confidence:

            Log:
            """ + log;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
    @Override
    public AIRecommendationResponse generateRecommendation(String ticketId) {
        logger.info("Generating recommendation for ticket: {}", ticketId);
        AIContext context = contextBuilder.build(ticketId);

        String prompt = promptBuilder.buildPrompt(context);
        try {
            String answer = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();
            logger.info("Recommendation generated successfully.");
            return new AIRecommendationResponse(ticketId, answer);
        }
        catch (Exception ex) {
            logger.error("Failed to generate recommendation for ticket: {}", ticketId, ex);
            return new AIRecommendationResponse(

                    ticketId,

                    "AI service is currently unavailable. Please ensure Ollama is running and the llm model is loaded."

            );
        }
    }
}
