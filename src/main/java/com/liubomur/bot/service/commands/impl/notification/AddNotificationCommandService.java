package com.liubomur.bot.service.commands.impl.notification;

import com.liubomur.bot.entity.User;
import com.liubomur.bot.constants.Commands;
import com.liubomur.bot.service.user.UserService;
import com.liubomur.bot.service.commands.CommandProcessor;
import com.liubomur.bot.utils.MethodExecutor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import static com.liubomur.bot.constants.CommandsResponse.*;
import static com.liubomur.bot.service.commands.impl.notification.AddNotificationUserStates.*;

@Service(Commands.ADDNOTIFICATION)
public class AddNotificationCommandService implements CommandProcessor {

    private final MethodExecutor methodExecutor;
    private final UserService userService;

    public AddNotificationCommandService(MethodExecutor methodExecutor, UserService userService) {
        this.methodExecutor = methodExecutor;
        this.userService = userService;
    }

    @Override
    public void initCommandProcessing(Message message, User user) {
        methodExecutor.sendMessage(message.getChatId(), ADDNOTIFICATION_COMMAND_RESPONSE_PET_NAME);

        updateUserCurrentCommand(user, Commands.ADDNOTIFICATION);
        updateUserCommandState(user, WAITING_PET_NAME);
    }

    @Override
    public void processCommandStep(Message message, User user) {
        AddNotificationUserStates state = AddNotificationUserStates.valueOf(user.getCommandState());
        switch (state) {
            case WAITING_PET_NAME -> processWaitingPetName(message, user);
            case WAITING_NOTIFICATION_FREQUENCY ->  processWaitingNotificationFrequency(message, user);
        }
    }

    private void updateUserCurrentCommand(User user, String commandName) {
        user.setCurrentCommand(commandName);
        userService.updateUser(user);
    }

    void updateUserCommandState(User user, AddNotificationUserStates commandState) {
        user.setCommandState(commandState.name());
        userService.updateUser(user);
    }

    private void processWaitingPetName(Message message, User user) {
        methodExecutor.sendMessage(message.getChatId(), ADDNOTIFICATION_COMMAND_RESPONSE_FREQUENCY);

        updateUserCommandState(user, WAITING_NOTIFICATION_FREQUENCY);
    }

    private void processWaitingNotificationFrequency(Message message, User user) {
        if (convertStringToInteger(message.getText()) <= 0) {
            methodExecutor.sendMessage(message.getChatId(), ADDNOTIFICATION_COMMAND_RESPONSE_INCORRECT_USER_INPUT);
            return;
        }

        methodExecutor.sendMessage(message.getChatId(), ADDNOTIFICATION_COMMAND_RESPONSE_FINISHED);
        updateUserCurrentCommand(user, Commands.START);
    }

    private int convertStringToInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
