package com.harshad.ai.client;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.harshad.ai.config.AiProperties;
import com.harshad.ai.exception.AiClientException;
import com.harshad.ai.model.openai.Message;
import com.harshad.ai.model.openai.OpenAiRequest;
import com.harshad.ai.model.openai.OpenAiResponse;

@Component
@Primary
@ConditionalOnProperty(
        name = "ai.provider",
        havingValue = "openai")
public class OpenAiClient implements AiClient {

    private final RestClient restClient;
    private final AiProperties aiProperties;

    public OpenAiClient(AiProperties aiProperties) {

        this.aiProperties = aiProperties;

        this.restClient = RestClient.builder()
                .baseUrl(aiProperties.getBaseUrl())
                .build();
    }

    @Override
    public String generate(String prompt) {

        OpenAiRequest request = new OpenAiRequest(
                aiProperties.getModel(),
                List.of(new Message("user", prompt)));
        try{
                OpenAiResponse response = restClient.post()
                                .uri("/chat/completions")
                                .header("Authorization", "Bearer " + aiProperties.getApiKey())
                                .header("Content-Type", "application/json")
                                .body(request)
                                .retrieve()
                                .body(OpenAiResponse.class);

                        return response.getChoices()
                                .get(0)
                                .getMessage()
                                .getContent();
        }catch (Exception e){
             System.out.println("Error occurred while communicating with OpenAI: " + e.getMessage());   
            throw new AiClientException(
            "Failed to communicate with OpenAI"+e.getMessage(),
            e);
        }        
        }
}