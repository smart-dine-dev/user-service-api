package com.devstack.SmartDine.service.impl;

import com.devstack.SmartDine.dtos.req.UpdateProfileRequestDto;
import com.devstack.SmartDine.dtos.resp.UserResponseDto;
import com.devstack.SmartDine.service.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public UserResponseDto getProfile(UUID userId) {
        return null;
    }

    @Override
    public UserResponseDto updateProfile(UUID userId, UpdateProfileRequestDto dto) {
        return null;
    }

    @Override
    public void deleteAccount(UUID userId) {

    }
}
