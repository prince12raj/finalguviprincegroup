package com.princerajgroup.votingsystem.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.princerajgroup.votingsystem.service.UserService;
import com.votingsystem.model.User;

@RestController // Indicates that this class is a REST controller
@RequestMapping("/api/users") // Base URL for all user-related endpoints
public class UserController {

    private final UserService userService;

    @Autowired // Automatically injects UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}") // Endpoint to get a user by username
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return Optional.ofNullable(userService.getUserByUsername(username));
    }

    // Additional endpoints can be added here for user-related operations
}
