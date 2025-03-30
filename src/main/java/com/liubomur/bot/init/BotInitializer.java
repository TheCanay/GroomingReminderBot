package com.liubomur.bot.init;

import com.liubomur.bot.GroomingReminderBot;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * This service class serves only one purpose - starting a telegram bot.
 */
@Service
public class BotInitializer {

    private static final Logger logger = LoggerFactory.getLogger(BotInitializer.class);
    private final GroomingReminderBot bot;

    @Value("${bot.token}")
    private String botToken;

    public BotInitializer(GroomingReminderBot bot) {
        this.bot = bot;
    }

    @PostConstruct
    void initBot() {
        //This piece should not be rewritten into try-with-resources as it will close a telegram bot.
        try {
            TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
            botsApplication.registerBot(botToken, bot);
        } catch (TelegramApiException e) {
            logger.error("initBot - failed to initialize bot due to error", e);
        }
    }


}
