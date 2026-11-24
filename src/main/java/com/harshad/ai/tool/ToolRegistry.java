package com.harshad.ai.tool;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ToolRegistry {

    private final List<Tool> tools;

    public ToolRegistry(List<Tool> tools) {
        this.tools = tools;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public Tool getTool(String name) {

        return tools.stream()
                .filter(tool -> tool.getName().equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Tool not found: " + name));
    }
}