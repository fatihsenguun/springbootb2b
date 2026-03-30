package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoRefreshToken;
import com.fatihsengun.dto.DtoRefreshTokenIU;
import com.fatihsengun.entity.RefreshToken;
import com.fatihsengun.entity.User;
import com.fatihsengun.repository.RefreshTokenRepository;
import com.fatihsengun.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService implements IRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;



    @Override
    public RefreshToken saveRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpireDate(LocalDateTime.now().plusDays(10));


        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public DtoRefreshToken refreshToken(DtoRefreshTokenIU dtoRefreshTokenIU) {
        return null;
    }
}
