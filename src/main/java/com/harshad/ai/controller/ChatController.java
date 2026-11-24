package com.harshad.ai.controller;

import com.harshad.ai.model.api.ChatRequest;
import com.harshad.ai.model.api.ChatResponse;
import com.harshad.ai.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {

        String reply = chatService.getReply(request.getMessage());

        return new ChatResponse(reply);
    }
}