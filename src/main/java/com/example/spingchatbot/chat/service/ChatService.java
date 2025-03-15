package com.example.spingchatbot.chat.service;

import com.example.spingchatbot.chat.exception.ChatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

    public String processMessage(final String messageFromUser) {
        try {
            return Objects.requireNonNull(chatClient.prompt().user(messageFromUser).call()
                            .chatResponse())
                    .getResult()
                    .getOutput()
                    .getText();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ChatException(e.getMessage());
        }
    }
}
