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

}
