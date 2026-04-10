package com.fatihsengun.service;

import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoLogin;
import com.fatihsengun.dto.DtoRegister;
import com.fatihsengun.dto.DtoUser;

public interface IUserService {

    public AuthResponse login(DtoLogin dtoLogin);

    public AuthResponse register(DtoRegister dtoRegister);

    public DtoUser me();
}
