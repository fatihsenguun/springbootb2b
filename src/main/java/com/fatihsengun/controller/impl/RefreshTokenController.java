package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRefreshTokenController;
import com.fatihsengun.dto.DtoRefreshToken;
import com.fatihsengun.dto.DtoRefreshTokenIU;
import com.fatihsengun.service.impl.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshTokenController implements IRefreshTokenController {

    @Autowired
    private RefreshTokenService refreshTokenService;


    @Override
    @PostMapping("/refresh")
    public DtoRefreshToken refresh(DtoRefreshTokenIU dtoRefreshTokenIU) {
        return refreshTokenService.refreshToken(dtoRefreshTokenIU);
    }
}
