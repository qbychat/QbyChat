package org.cubewhy.qbychat.services.impl;

import jakarta.annotation.Resource;
import org.cubewhy.qbychat.component.JwtTokenProvider;
import org.cubewhy.qbychat.entity.dto.LoginDTO;
import org.cubewhy.qbychat.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    AuthenticationManager authenticationManager;
    @Resource
    JwtTokenProvider jwtTokenProvider;


    @Override
    public String login(LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
}
