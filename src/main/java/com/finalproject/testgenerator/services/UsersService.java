package com.finalproject.testgenerator.services;

import com.finalproject.testgenerator.models.User;
import com.finalproject.testgenerator.repositories.UsersRepository;
import jdk.internal.dynalink.support.NameCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService{
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findByUsername(String username) {
        User user = usersRepository.findByUsername(username);

        if (user == null) {
            return null;
        }
        return user;
    }

    public User create(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }
}