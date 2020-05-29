package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.DTOs.UserDTO;
import com.finalproject.testgenerator.models.User;
import com.finalproject.testgenerator.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private UsersService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UsersController(UsersService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User create(@RequestBody UserDTO user,
                       @RequestHeader(name = "Authorization") String token) {
        return userService.create(modelMapper.map(user, User.class));
    }
}
