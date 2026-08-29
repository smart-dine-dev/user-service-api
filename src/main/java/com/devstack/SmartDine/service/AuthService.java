package com.devstack.SmartDine.service;

import com.devstack.SmartDine.dtos.req.*;
import com.devstack.SmartDine.dtos.resp.AuthResponseDto;
import com.devstack.SmartDine.dtos.resp.TokenRefreshResponseDto;

public interface AuthService {
    void signup(SignupRequestDto dto);
    AuthResponseDto login(LoginRequestDto dto);
    AuthResponseDto loginWithGoogle(GoogleLoginRequestDto dto);
    AuthResponseDto loginWithGitHub(GitHubLoginRequestDto dto);
    TokenRefreshResponseDto refreshToken(TokenRefreshRequestDto dto);
    void logout(String refreshToken);
}
