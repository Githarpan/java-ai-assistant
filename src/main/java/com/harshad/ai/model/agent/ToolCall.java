
package com.harshad.ai.model.agent;

public class ToolCall {

    private String tool;
    private String arguments;

    public ToolCall() {
    }

    public ToolCall(String tool, String arguments) {
        this.tool = tool;
        this.arguments = arguments;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
}
