package com.harshad.ai.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.harshad.ai.config.AiProperties;
import com.harshad.ai.exception.AiClientException;
import com.harshad.ai.ollama.OllamaRequest;
import com.harshad.ai.ollama.OllamaResponse;

@Component
@ConditionalOnProperty(
        name = "ai.provider",
        havingValue = "ollama")
public class OllamaClient implements AiClient {

    private final RestClient restClient;
    private final AiProperties aiProperties;

    public OllamaClient(AiProperties aiProperties) {

        this.aiProperties = aiProperties;

        this.restClient = RestClient.builder()
                .baseUrl(aiProperties.getBaseUrl())
                .build();
    }
    
    @Override
    public String generate(String prompt) {

        OllamaRequest request =
                new OllamaRequest(
                        aiProperties.getModel(),
                        prompt,false);

        try {

            OllamaResponse response = restClient.post()
                    .uri("/api/generate")
                    .body(request)
                    .retrieve()
                    .body(OllamaResponse.class);

            return response.getResponse();

        } catch (RestClientException ex) {

            System.out.println(
                    "Error occurred while communicating with Ollama: "
                            + ex.getMessage());

            throw new AiClientException(
                    "Failed to communicate with Ollama: "
                            + ex.getMessage(),
                    ex);
        }
    }

}
