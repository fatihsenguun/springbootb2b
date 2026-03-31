package com.fatihsengun.mapper;

import com.fatihsengun.dto.*;
import com.fatihsengun.entity.BusinessProfile;
import com.fatihsengun.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    AuthResponse toAuthResponseLogin(DtoLogin dtoLogin);

    BusinessProfile toBusinessProfileEntity(DtoBusinessProfileIU dtoBusinessProfileIU);

    @Mapping(target = "user", ignore = true)
    DtoBusinessProfile toDtoBusinessProfile(BusinessProfile businessProfile);


    DtoUser toDtoUser(User user);

}
