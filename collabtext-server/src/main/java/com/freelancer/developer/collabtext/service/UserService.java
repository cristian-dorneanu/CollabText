package com.freelancer.developer.collabtext.service;

import com.freelancer.developer.collabtext.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    Page<User> retrieveAllUsers(Pageable pageable);
    User retrieveUserById(Long userId);
    User updateUserById(Long userId, User user);
    void addUser(User user);
    void removeUserById(Long userId);
}
