package com.liubomur.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class BeansConfiguration {

    @Value("${bot.token}")
    private String botToken;

    @Bean
    TelegramClient telegramClient() {
        return new OkHttpTelegramClient(botToken);
    }

}
