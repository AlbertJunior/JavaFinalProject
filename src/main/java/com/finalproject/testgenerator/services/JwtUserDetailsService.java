package com.finalproject.testgenerator.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * This class represents a service for authenticate a user
 * It overrides the loadUserByUsername for fetching user details from the database using the username.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UsersService userService;
    private Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);


    @Autowired
    public JwtUserDetailsService (UsersService usersService){
        this.userService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.finalproject.testgenerator.models.User user = userService.findByUsername(username);

        if (user == null) {
            logger.error("Username was not found");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        logger.error("Successful authentication");
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>()); //no rules for the user
    }
}


