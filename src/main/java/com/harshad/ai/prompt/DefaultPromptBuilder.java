package com.harshad.ai.prompt;

import org.springframework.stereotype.Component;

@Component
public class DefaultPromptBuilder implements PromptBuilder {

    @Override
    public String build(String userMessage) {

        return """
                You are a helpful AI assistant.Answer the user's question clearly.User Message: %s""".formatted(userMessage);
    }
}