package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(final Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public User changeUserStatus(final Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setStatus(!user.isStatus());
        return user;
    }

    public User changeUserKey(final Long userId) throws UserNotFoundException {
        Random generator = new Random();
        int newKey = generator.nextInt(10000);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setUserKey(newKey);
        return user;
    }
}