package com.fatihsengun.controller;

import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoLogin;
import com.fatihsengun.dto.DtoRegister;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.entity.RootResponseEntity;

public interface IUserController {

    public RootResponseEntity<AuthResponse> login(DtoLogin dtoLogin);

    public RootResponseEntity<AuthResponse> register(DtoRegister dtoRegister);

    public RootResponseEntity<DtoUser> me();

}
