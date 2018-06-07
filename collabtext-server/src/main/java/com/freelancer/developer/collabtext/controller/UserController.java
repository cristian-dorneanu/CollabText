package com.freelancer.developer.collabtext.controller;

import com.freelancer.developer.collabtext.model.User;
import com.freelancer.developer.collabtext.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.retrieveAllUsers(pageable);
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.retrieveUserById(userId);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userToUpdate) {
        return userService.updateUserById(userId, userToUpdate);
    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.removeUserById(userId);
        return ResponseEntity.ok().build();
    }
}
