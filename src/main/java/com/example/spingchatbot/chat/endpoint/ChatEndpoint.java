package com.example.spingchatbot.chat.endpoint;


import com.example.spingchatbot.chat.endpoint.model.request.ChatMessageRequest;
import com.example.spingchatbot.chat.endpoint.model.response.ChatResponse;
import com.example.spingchatbot.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatEndpoint {

    private final ChatService chatService;

    @PostMapping
    public ChatResponse sendMessage(@Valid @RequestBody final ChatMessageRequest request) {
        return new ChatResponse(chatService.processMessage(request.message()));
    }
}
