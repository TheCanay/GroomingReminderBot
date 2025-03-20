package com.liubomur.bot.service;

import com.liubomur.bot.entity.User;
import com.liubomur.bot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
