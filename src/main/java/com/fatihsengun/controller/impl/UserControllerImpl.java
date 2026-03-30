package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IUserController;
import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoLogin;
import com.fatihsengun.dto.DtoRegister;
import com.fatihsengun.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements IUserController {

    @Autowired
    private UserServiceImpl userService;


    @Override
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody DtoLogin dtoLogin) {
        return userService.login(dtoLogin);
    }

    @Override
    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody DtoRegister dtoRegister) {
        return userService.register(dtoRegister);
    }
}
