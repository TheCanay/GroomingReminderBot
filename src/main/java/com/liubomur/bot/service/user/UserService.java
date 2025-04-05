package com.liubomur.bot.service.user;

import com.liubomur.bot.entity.User;
import com.liubomur.bot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import static com.liubomur.bot.constants.Commands.START;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrSaveTelegramUser(Message message) {
        long userId = message.getFrom().getId();
        if (userExists(userId)) {
            return getUserById(userId);
        } else {
            return save(new User(userId, START));
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

}
