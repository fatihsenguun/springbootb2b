package com.fatihsengun.mapper;

import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoLogin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    AuthResponse toAuthResponseLogin(DtoLogin dtoLogin);




}
