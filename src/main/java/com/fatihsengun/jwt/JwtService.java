package com.fatihsengun.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SECRET_KEY = "KtRH5USEtAUzn5f0tKNNDVCekRdE5Irpme7JP7CIi6o=KtRH5USEtAUzn5f0tKNNDVCekRdE5Irpme7JP7CIi6o=";

    public String generateToken(UserDetails userDetails) {

        String role = userDetails.getAuthorities().iterator().next().getAuthority();


        return Jwts.builder()
                .claim("role", role)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(getKey())
                .compact();
    }


    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T exportToken(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsTFunction.apply(claims);
    }


    public String getRoleByToken(String token) {
        return exportToken(token, claims -> claims.get("role", String.class));
    }

    public String getUsernameByToken(String token){return exportToken(token,Claims::getSubject);}

    public boolean isTokenExpired(String token){
        Date expiredDate= exportToken(token,Claims::getExpiration);
        return new Date().before(expiredDate);

    }

}
