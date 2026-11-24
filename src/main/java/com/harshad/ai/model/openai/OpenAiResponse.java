package com.harshad.ai.model.openai;

import java.util.List;

public class OpenAiResponse {

    private List<Choice> choices;

    public OpenAiResponse() {
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}