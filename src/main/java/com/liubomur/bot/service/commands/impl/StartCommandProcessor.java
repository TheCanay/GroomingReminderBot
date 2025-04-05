package com.liubomur.bot.service.commands.impl;

import com.liubomur.bot.entity.User;
import com.liubomur.bot.service.commands.CommandProcessor;
import com.liubomur.bot.utils.MethodExecutor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import static com.liubomur.bot.constants.CommandsResponse.START_COMMAND_INIT_RESPONSE;
import static com.liubomur.bot.constants.CommandsResponse.START_COMMAND_DEFAULT_RESPONSE;
import static com.liubomur.bot.constants.Commands.START;

@Service(START)
public class StartCommandProcessor implements CommandProcessor {

    private final MethodExecutor methodExecutor;

    public StartCommandProcessor(MethodExecutor methodExecutor) {
        this.methodExecutor = methodExecutor;
    }

    @Override
    public void initCommandProcessing(Message message, User user) {
        methodExecutor.sendMessage(message.getChatId(), START_COMMAND_INIT_RESPONSE);
    }

    @Override
    public void processCommandStep(Message command, User user) {
        methodExecutor.sendMessage(command.getChatId(), START_COMMAND_DEFAULT_RESPONSE);
    }

}
