package com.example.spingchatbot.chat.endpoint.model.request;

import javax.validation.constraints.NotNull;

public record ChatMessageRequest(@NotNull String message) {
}
