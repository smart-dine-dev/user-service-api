package com.devstack.SmartDine.service.impl;

import com.devstack.SmartDine.dtos.req.*;
import com.devstack.SmartDine.dtos.resp.AuthResponseDto;
import com.devstack.SmartDine.dtos.resp.TokenRefreshResponseDto;
import com.devstack.SmartDine.service.AuthService;

public class AuthServiceImpl implements AuthService {
    @Override
    public void signup(SignupRequestDto dto) {

    }

    @Override
    public AuthResponseDto login(LoginRequestDto dto) {
        return null;
    }

    @Override
    public AuthResponseDto loginWithGoogle(GoogleLoginRequestDto dto) {
        return null;
    }

    @Override
    public AuthResponseDto loginWithGitHub(GitHubLoginRequestDto dto) {
        return null;
    }

    @Override
    public TokenRefreshResponseDto refreshToken(TokenRefreshRequestDto dto) {
        return null;
    }

    @Override
    public void logout(String refreshToken) {

    }
}
