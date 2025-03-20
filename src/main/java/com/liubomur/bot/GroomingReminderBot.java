package com.liubomur.bot;

import com.liubomur.bot.entity.User;
import com.liubomur.bot.enums.states.UserState;
import com.liubomur.bot.service.AddNotificationCommandService;
import com.liubomur.bot.enums.commands.Commands;
import com.liubomur.bot.service.UserService;
import com.liubomur.bot.utils.MethodExecutor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import static com.liubomur.bot.constants.CommandsResponse.*;

@Component
public class GroomingReminderBot implements LongPollingSingleThreadUpdateConsumer {

    private final MethodExecutor methodExecutor;
    private final AddNotificationCommandService addNotificationService;
    private final UserService userService;

    public GroomingReminderBot(MethodExecutor methodExecutor, AddNotificationCommandService addNotificationService, UserService userService) {
        this.methodExecutor = methodExecutor;
        this.addNotificationService = addNotificationService;
        this.userService = userService;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            User user = getUserFromTelegram(message);
            if (user.getState() instanceof UserState) {}
            commandsActions(update.getMessage(), user);
        }
    }

    private User getUserFromTelegram(Message message) {
        long userId = message.getFrom().getId();
        if (userService.userExists(userId)) {
            return userService.getUserById(userId);
        } else {
            return userService.save(new User(userId, UserState.IDLE));
        }
    }

    private void commandsActions(Message command, User user) {
        Commands cmd = getCommand(command.getText());
        switch (cmd) {
            case START -> methodExecutor.sendMessage(command.getChatId(), START_COMMAND_RESPONSE);
            case ADDNOTIFICATION -> addNotificationService.addNotification(command, user);
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