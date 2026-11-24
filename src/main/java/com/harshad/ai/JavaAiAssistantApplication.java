package com.harshad.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class JavaAiAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAiAssistantApplication.class, args);
    }
}