package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoRefreshToken;
import com.fatihsengun.dto.DtoRefreshTokenIU;
import com.fatihsengun.entity.RefreshToken;
import com.fatihsengun.entity.RootResponseEntity;

public interface IRefreshTokenController {

    public RootResponseEntity<DtoRefreshToken> refresh(DtoRefreshTokenIU dtoRefreshTokenIU);

}
