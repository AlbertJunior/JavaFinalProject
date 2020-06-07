package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.User;
import com.finalproject.testgenerator.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This class represents a service for creating and login a user
 */
@Service
public class UsersService{
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private Logger logger = LoggerFactory.getLogger(UsersService.class);


    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findByUsername(String username) {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            logger.info("User " + username + " was not found");
            return null;
        }
        logger.info("User " + username + " was returned");
        return user;
    }


    public User create(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }
}