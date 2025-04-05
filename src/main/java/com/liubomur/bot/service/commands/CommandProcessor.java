package com.liubomur.bot.service.commands;

import org.telegram.telegrambots.meta.api.objects.message.Message;
import com.liubomur.bot.entity.User;

public interface CommandProcessor {

    void initCommandProcessing(Message message, User user);

    void processCommandStep(Message command, User user);

}
