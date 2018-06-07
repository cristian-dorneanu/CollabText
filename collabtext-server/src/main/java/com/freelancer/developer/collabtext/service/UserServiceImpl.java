package com.freelancer.developer.collabtext.service;

import com.freelancer.developer.collabtext.model.User;
import com.freelancer.developer.collabtext.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> retrieveAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User retrieveUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @Override
    public User updateUserById(Long userId, User userToUpdate) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(userToUpdate.getUsername());
            user.setEmail(userToUpdate.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() ->  new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUserById(Long userId) {
        userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }
}
