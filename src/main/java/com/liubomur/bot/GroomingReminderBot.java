package com.liubomur.bot;

import com.liubomur.bot.commands.Commands;
import com.liubomur.bot.utils.MethodExecutor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import static com.liubomur.bot.constants.CommandsResponse.*;

@Component
public class GroomingReminderBot implements LongPollingSingleThreadUpdateConsumer {

    private final MethodExecutor methodExecutor;

    public GroomingReminderBot(MethodExecutor methodExecutor) {
        this.methodExecutor = methodExecutor;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().isCommand()) {
            commandsActions(update.getMessage());
        }
    }

    private void commandsActions(Message command) {
        Commands cmd = getCommand(command.getText());
        switch (cmd) {
            case START -> methodExecutor.sendMessage(command.getChatId(), START_COMMAND_RESPONSE);
            case FREQUENCY -> methodExecutor.sendMessage(command.getChatId(), FREQUENCY_COMMAND_RESPONSE);
            case INVALID_COMMAND -> methodExecutor.sendMessage(command.getChatId(), INVALID_COMMAND_RESPONSE);
        }
    }

    private Commands getCommand(String commandText) {
        try {
            return Commands.valueOf(commandText.toUpperCase().replace("/", ""));
        } catch (IllegalArgumentException e) {
            return Commands.INVALID_COMMAND;
        }
    }
}