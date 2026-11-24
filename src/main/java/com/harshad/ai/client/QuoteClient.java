package com.harshad.ai.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.harshad.ai.model.response.QuoteResponse;

@Component
public class QuoteClient {

    private final RestClient restClient;

    public QuoteClient() {

        this.restClient = RestClient.builder()
                .baseUrl("https://api.quotable.io")
                .build();
    }

    public QuoteResponse getRandomQuote() {

        return restClient
                .get()
                .uri("/random")
                .retrieve()
                .body(QuoteResponse.class);
    }
}