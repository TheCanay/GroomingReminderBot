package com.liubomur.bot;

import com.liubomur.bot.entity.User;
import com.liubomur.bot.service.commands.CommandProcessorFactory;
import com.liubomur.bot.service.user.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Component
public class GroomingReminderBot implements LongPollingSingleThreadUpdateConsumer {

    private final CommandProcessorFactory  commandProcessorFactory;
    private final UserService userService;

    public GroomingReminderBot(CommandProcessorFactory commandProcessorFactory, UserService userService) {
        this.commandProcessorFactory = commandProcessorFactory;
        this.userService = userService;
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage()) {
            return;
        }

        Message message = update.getMessage();
        User user = userService.getOrSaveTelegramUser(message);

        if (message.isCommand()) {
            processCommandMessage(message, user);
            return;
        }

        if (message.hasText()) {
            processTextMessage(message, user);
        }
    }

    private void processTextMessage(Message message, User user) {
        commandProcessorFactory.getCommandProcessor(user.getCurrentCommand())
                .processCommandStep(message, user);
    }

    private void processCommandMessage(Message command, User user) {
        commandProcessorFactory.getCommandProcessor(command.getText())
            .initCommandProcessing(command, user);
    }

}