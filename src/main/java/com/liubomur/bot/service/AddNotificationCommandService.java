package com.liubomur.bot.service;

import com.liubomur.bot.entity.User;
import com.liubomur.bot.enums.states.UserState;
import com.liubomur.bot.utils.MethodExecutor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import static com.liubomur.bot.constants.CommandsResponse.*;

@Service
public class AddNotificationCommandService {

    private final MethodExecutor methodExecutor;
    private final UserService userService;

    public AddNotificationCommandService(MethodExecutor methodExecutor, UserService userService) {
        this.methodExecutor = methodExecutor;
        this.userService = userService;
    }

    public void addNotification(Message message, User user) {
        UserState userState = user.getState();
        switch (userState) {
            case IDLE -> processIdleUser(message, user);
            case WAITING_PET_NAME -> processWaitingPetName(message, user);
            case WAITING_NOTIFICATION_FREQUENCY -> processWaitingNotificationFrequency(message, user);
        }

    }

    private void processIdleUser(Message message, User user) {
        methodExecutor.sendMessage(message.getChatId(), ADDNOTIFICATION_COMMAND_RESPONSE_PET_NAME);
        user.setState(UserState.WAITING_PET_NAME);
        userService.updateUser(user);
    }

    private void processWaitingPetName(Message message, User user) {
        methodExecutor.sendMessage(message.getChatId(), ADDNOTIFICATION_COMMAND_RESPONSE_FREQUENCY);
        user.setState(UserState.WAITING_NOTIFICATION_FREQUENCY);
        userService.updateUser(user);
    }

    private void processWaitingNotificationFrequency(Message message, User user) {
        if (convertStringToInteger(message.getText()) <= 0) {
            methodExecutor.sendMessage(message.getChatId(), ADDNOTIFICATION_COMMAND_RESPONSE_INCORRECT_USER_INPUT);
            return;
        }

        methodExecutor.sendMessage(message.getChatId(), ADDNOTIFICATION_COMMAND_RESPONSE_FINISHED);
        user.setState(UserState.IDLE);
        userService.updateUser(user);
    }

    private int convertStringToInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
