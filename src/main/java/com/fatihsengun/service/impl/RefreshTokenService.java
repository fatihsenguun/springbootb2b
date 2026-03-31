package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoRefreshToken;
import com.fatihsengun.dto.DtoRefreshTokenIU;
import com.fatihsengun.entity.RefreshToken;
import com.fatihsengun.entity.User;
import com.fatihsengun.jwt.JwtService;
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

    @Autowired
    private JwtService jwtService;


    @Override
    public RefreshToken saveRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpireDate(LocalDateTime.now().plusDays(10));


        return refreshTokenRepository.save(refreshToken);
    }

    public boolean isRefreshTokenExpired(LocalDateTime expiredDate) {
        return LocalDateTime.now().isAfter(expiredDate);
    }

    @Override
    public DtoRefreshToken refreshToken(DtoRefreshTokenIU dtoRefreshTokenIU) {

        RefreshToken optional = refreshTokenRepository
                .findRefreshTokenByRefreshToken(dtoRefreshTokenIU.getRefreshToken())
                .orElse(null);

        if (isRefreshTokenExpired(optional.getExpireDate())) {
            System.out.println("expired");
        }
        String accessToken = jwtService.generateToken(optional.getUser());

        RefreshToken savedRefreshToken = saveRefreshToken(optional.getUser());
        refreshTokenRepository.delete(optional);

        return new DtoRefreshToken(accessToken, savedRefreshToken.getRefreshToken());
    }
}
