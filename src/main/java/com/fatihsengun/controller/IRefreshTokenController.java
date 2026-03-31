package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoRefreshToken;
import com.fatihsengun.dto.DtoRefreshTokenIU;
import com.fatihsengun.entity.RefreshToken;

public interface IRefreshTokenController {

    public DtoRefreshToken refresh(DtoRefreshTokenIU dtoRefreshTokenIU);

}
