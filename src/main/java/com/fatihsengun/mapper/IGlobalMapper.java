package com.fatihsengun.mapper;

import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoBusinessProfile;
import com.fatihsengun.dto.DtoBusinessProfileIU;
import com.fatihsengun.dto.DtoLogin;
import com.fatihsengun.entity.BusinessProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    AuthResponse toAuthResponseLogin(DtoLogin dtoLogin);

    BusinessProfile toBusinessProfileEntity(DtoBusinessProfileIU dtoBusinessProfileIU);

    DtoBusinessProfile toDtoBusinessProfile(BusinessProfile businessProfile);


}
