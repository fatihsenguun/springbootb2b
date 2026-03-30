package com.fatihsengun.controller;

import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoLogin;
import com.fatihsengun.dto.DtoRegister;

public interface IUserController {

    public AuthResponse login(DtoLogin dtoLogin);

    public AuthResponse register(DtoRegister dtoRegister);

}
