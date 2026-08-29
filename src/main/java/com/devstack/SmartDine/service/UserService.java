package com.devstack.SmartDine.service;

import com.devstack.SmartDine.dtos.req.UpdateProfileRequestDto;
import com.devstack.SmartDine.dtos.resp.UserResponseDto;

import java.util.UUID;

public interface UserService {
    UserResponseDto getProfile(UUID userId);
    UserResponseDto updateProfile(UUID userId, UpdateProfileRequestDto dto);
    void deleteAccount(UUID userId);
}
