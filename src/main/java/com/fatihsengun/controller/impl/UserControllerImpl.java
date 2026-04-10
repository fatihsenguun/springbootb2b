package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IUserController;
import com.fatihsengun.controller.RestRootResponseController;
import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoLogin;
import com.fatihsengun.dto.DtoRegister;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl extends RestRootResponseController implements IUserController {

    @Autowired
    private UserServiceImpl userService;


    @Override
    @PostMapping("/login")
    public RootResponseEntity<AuthResponse> login(@Valid @RequestBody DtoLogin dtoLogin) {
        return ok(userService.login(dtoLogin));
    }

    @Override
    @PostMapping("/register")
    public RootResponseEntity<AuthResponse> register(@Valid @RequestBody DtoRegister dtoRegister) {
        return ok(userService.register(dtoRegister));
    }

    @Override
    @GetMapping("/me")
    public RootResponseEntity<DtoUser> me() {
        return ok(userService.me());
    }
}
