package com.example.spingchatbot.chat.service;

import com.example.spingchatbot.chat.exception.ChatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

    private static final List<Message> chatHistory = new ArrayList<>();

    public String processMessage(final String messageFromUser) {
        try {
            addUserMessage(messageFromUser);
            return getChatResponse();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ChatException(e.getMessage());
        }
    }

    private String getChatResponse() {
        ChatResponse chatResponse = chatClient.prompt().messages(chatHistory).call().chatResponse();
        String textResponse = chatResponse.getResults().getFirst().getOutput().getText();
        addAssistantResponse(textResponse);
        return textResponse;
    }

    private void addUserMessage(String message) {
        chatHistory.add(new UserMessage(message));
    }

    private void addAssistantResponse(String message) {
        chatHistory.add(new AssistantMessage(message));
    }
}
