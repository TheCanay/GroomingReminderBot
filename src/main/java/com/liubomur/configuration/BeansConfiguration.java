package com.liubomur.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import static com.liubomur.bot.constants.Constants.BOT_TOKEN;

@Configuration
public class BeansConfiguration {

    @Bean
    TelegramClient telegramClient() {
        return new OkHttpTelegramClient(BOT_TOKEN);
    }

}
