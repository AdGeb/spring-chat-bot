package com.example.spingchatbot.config;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.http.HttpClient;
import com.azure.core.util.HttpClientOptions;
import org.springframework.ai.autoconfigure.azure.openai.AzureOpenAIClientBuilderCustomizer;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ChatClientConfig {

    @Bean
    public AzureOpenAIClientBuilderCustomizer responseTimeoutCustomizer() {
        return openAiClientBuilder -> {
            HttpClientOptions clientOptions = new HttpClientOptions()
                    .setResponseTimeout(Duration.ofMinutes(5));
            openAiClientBuilder.httpClient(HttpClient.createDefault(clientOptions));
        };
    }

    @Bean
    public ChatClient chatClient(AzureOpenAiChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem("You are a villain")
                .build();
    }
}
