package com.harshad.ai.service;

import org.springframework.stereotype.Service;

import com.harshad.ai.client.AiClient;
import com.harshad.ai.model.response.AgentResponse;
import com.harshad.ai.tool.Tool;
import com.harshad.ai.tool.ToolRegistry;

@Service
public class AgentService {

    private final AiClient aiClient;
    private final ToolRegistry toolRegistry;

    public AgentService(
            AiClient aiClient,
            ToolRegistry toolRegistry) {

        this.aiClient = aiClient;
        this.toolRegistry = toolRegistry;
    }

    public String process(String userMessage) {

        String prompt = """
                You are an AI agent.

                Available tool:
                - get_current_date_time:
                  Returns the current date and time from the system clock.

                User request:
                %s

                Decide whether you need the tool.
                If you need the tool, respond exactly in this format:

                TOOL:get_current_date_time

                Otherwise respond with:

                FINAL:<your answer>
                """.formatted(userMessage);

        String llmResponse = aiClient.generate(prompt);

        AgentResponse response = parseResponse(llmResponse);

        if ("TOOL".equalsIgnoreCase(response.getType())) {

            Tool tool = toolRegistry.getTool(response.getToolName());

            String toolResult = tool.execute("");

            String finalPrompt = """
                    You are an AI agent.

                    User request:
                    %s

                    Tool used:
                    %s

                    Tool result:
                    %s

                    Provide the final answer to the user.
                    """.formatted(
                            userMessage,
                            response.getToolName(),
                            toolResult);

            return aiClient.generate(finalPrompt);
        }

        return response.getContent();
    }

    private AgentResponse parseResponse(String response) {

        AgentResponse result = new AgentResponse();

        if (response.startsWith("TOOL:")) {

            result.setType("TOOL");
            result.setToolName(
                    response.substring("TOOL:".length()).trim());

        } else if (response.startsWith("FINAL:")) {

            result.setType("FINAL");
            result.setContent(
                    response.substring("FINAL:".length()).trim());

        } else {

            result.setType("FINAL");
            result.setContent(response);
        }

        return result;
    }
}