package com.harshad.ai.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "ai.mock.enabled", havingValue = "mock")
public class MockAiClient implements AiClient {

    @Override
    public String generate(String prompt) {

        return """
                ===== MOCK AI =====

                Prompt Received:

                %s

                ===================
                """.formatted(prompt);
    }
}