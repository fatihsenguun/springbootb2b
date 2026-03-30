package com.fatihsengun.service.impl;

import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoLogin;
import com.fatihsengun.dto.DtoRegister;
import com.fatihsengun.entity.User;
import com.fatihsengun.enums.Role;
import com.fatihsengun.jwt.JwtService;
import com.fatihsengun.repository.UserRepository;
import com.fatihsengun.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public AuthResponse login(DtoLogin dtoLogin) {
        try {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(dtoLogin.getEmail(), dtoLogin.getPassword());
            authenticationProvider.authenticate(auth);

            User user = userRepository.findByEmail(dtoLogin.getEmail())
                    .orElse(null);
            String accessToken = jwtService.generateToken(user);
            return new AuthResponse(accessToken, "");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    @Transactional
    public AuthResponse register(DtoRegister dtoRegister) {
        User user = new User();
        user.setRole(Role.ROLE_USER);
        user.setFullName(dtoRegister.getFullName());
        user.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        user.setEmail(dtoRegister.getEmail());

        userRepository.save(user);

        DtoLogin dtoLogin = new DtoLogin();
        dtoLogin.setEmail(dtoRegister.getEmail());
        dtoLogin.setPassword(dtoRegister.getPassword());

        return login(dtoLogin);
    }
}
