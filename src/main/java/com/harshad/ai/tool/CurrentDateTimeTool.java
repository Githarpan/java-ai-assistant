package com.harshad.ai.tool;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class CurrentDateTimeTool implements Tool {

    @Override
    public String getName() {
        return "get_current_date_time";
    }

    @Override
    public String getDescription() {
        return "Returns the current date and time from the system clock.";
    }

    @Override
    public String execute(String input) {
        return LocalDateTime.now().toString();
    }
}