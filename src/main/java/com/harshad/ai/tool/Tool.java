package com.harshad.ai.tool;

public interface Tool {

    String getName();

    String getDescription();

    String execute(String input);
}