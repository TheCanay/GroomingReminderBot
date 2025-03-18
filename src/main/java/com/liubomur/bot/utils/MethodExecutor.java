package com.liubomur.bot.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.io.Serializable;

@Component
public class MethodExecutor {

    private final TelegramClient telegramClient;

    public MethodExecutor(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    public void sendMessage(Long chatId, String message) {
        executeMethod(SendMessage.builder()
                .chatId(chatId)
                .text(message)
                .build());
    }

    public  <T extends Serializable, Method extends BotApiMethod<T>> T executeMethod(Method botApiMethod) {
        try {
            return telegramClient.execute(botApiMethod);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
