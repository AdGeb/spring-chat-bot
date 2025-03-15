package com.example.spingchatbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.ai.azure.openai")
public record OpenAIClientProperties(
        String apiKey,
        String endpoint) {
}
