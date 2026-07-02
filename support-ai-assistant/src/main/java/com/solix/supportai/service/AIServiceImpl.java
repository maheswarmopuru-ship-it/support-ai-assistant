package com.solix.supportai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {

    private final ChatClient chatClient;

    public AIServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
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

}
