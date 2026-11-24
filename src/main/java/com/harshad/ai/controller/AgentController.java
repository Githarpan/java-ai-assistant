package com.harshad.ai.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.harshad.ai.service.AgentService;
import com.harshad.ai.model.api.ChatRequest;
import com.harshad.ai.model.api.ChatResponse;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public ChatResponse agent(
            @Valid @RequestBody ChatRequest request) {

                System.err.println("AgentController: Received request on 1510: " + request.getMessage());
        String response =
                agentService.process(request.getMessage());

        return new ChatResponse(response);
    }
}