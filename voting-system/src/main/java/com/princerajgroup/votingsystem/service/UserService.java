package com.princerajgroup.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.princerajgroup.votingsystem.repository.UserRepository;
import com.votingsystem.model.User;

@Service // Indicates that this class is a service
public class UserService {

    private final UserRepository userRepository;

    @Autowired // Automatically injects UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Additional methods to handle user operations can be added here
}
