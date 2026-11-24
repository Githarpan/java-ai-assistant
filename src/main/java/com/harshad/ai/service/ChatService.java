package com.harshad.ai.service;

import com.harshad.ai.client.AiClient;
import com.harshad.ai.prompt.PromptBuilder;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final AiClient aiClient;
    private final PromptBuilder promptBuilder;

    public ChatService(
            AiClient aiClient,
            PromptBuilder promptBuilder) {

        this.aiClient = aiClient;
        this.promptBuilder = promptBuilder;
    }

    public String getReply(String message) {

        String prompt = promptBuilder.build(message);

        return aiClient.generate(prompt);
    }
}